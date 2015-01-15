/*
 * Contains methods to execute a scenario
 */
package scenario;

import scenario.model.ConsumerTask;
import scenario.model.TaskResult;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import scenario.model.MessageResult;

/**
 *
 * @author JusteAbel
 */
public class ScenarioManager {

    /**
     * The scenario
     */
    /**
     * The scenario : tasks to execute
     */
    private ArrayList<ConsumerTask> tasks;

    /**
     * The results of the emulation
     */
    private ArrayList<TaskResult> results;
    
    /**
     * The timeout in minutes
     */
    private final int MAX_RTT = 30;

    public ScenarioManager() {
        this.tasks = new ArrayList<>();
        this.results = new ArrayList<>();
    }

    /**
     * Run scenario
     */
    public void runScenario() {
        for (ConsumerTask task : tasks) {
            results.add(executeTask(task));
        }
    }

    /**
     * Checks whether a valid scenario has been provided
     *
     * @return
     */
    public boolean hasScenario() {
        return !this.tasks.isEmpty();
    }

    /**
     * Sends message a a given rate during a given time
     */
    private TaskResult executeTask(ConsumerTask task) {
        long startTime = System.currentTimeMillis();
        ArrayList<FutureTask<MessageResult>> msgSenderList = new ArrayList<>();
        TaskResult taskResult = new TaskResult();
        ExecutorService execService = Executors.newCachedThreadPool();
        
        //start a message sender (thread) each period during the given duration
        while ((System.currentTimeMillis() - startTime) < task.getDuration()) {

            MessageSender msgSender = new MessageSender(task.produceMessage(), task.getProducerWsdl(),
                    task.getProcessingTime(), task.getRequestSize(), task.getResponseSize());
            FutureTask<MessageResult> senderTask = new FutureTask<>(msgSender);
            msgSenderList.add(senderTask);
            execService.execute(senderTask);
            try {
                Thread.sleep(task.getPeriod());
            } catch (InterruptedException ex) {
                Logger.getLogger(ScenarioManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //Wait and stop the message senders after a given duration (timeout)
        for(FutureTask<MessageResult> msgSender : msgSenderList){
            try {
                MessageResult msgResult = msgSender.get(MAX_RTT+task.getProcessingTime(), TimeUnit.MINUTES);
                taskResult.getTimeToSend().add(msgResult.getRequestTime());
                taskResult.getTimeToReceive().add(msgResult.getResponseTime());
                
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(ScenarioManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TimeoutException ex) {
                //report loss
                taskResult.getTimeToSend().add(new Long(-1));
                taskResult.getTimeToReceive().add(new Long(-1));
            }
        }
        execService.shutdown();
        return taskResult;
        
    }

    /*----------------- Getters and setters **********************************/
    public ArrayList<ConsumerTask> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<ConsumerTask> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<TaskResult> getResults() {
        return results;
    }

    public void setResults(ArrayList<TaskResult> results) {
        this.results = results;
    }

}
