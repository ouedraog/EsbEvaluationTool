/*
 * Service for executing the scenario deployment and results retrieving tasks
 */
package org.insa.tasks;

import java.net.MalformedURLException;
import java.util.ArrayList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.insa.model.Model;
import org.insa.model.beans.Consumer;
import org.insa.model.beans.Distribution;
import org.insa.model.beans.KPI;

/**
 *
 * @author JusteAbel
 */
public class ScenarioService extends Service<Void> {

    /**
     * The model
     */
    private Model model;
    private int finishedTasksNumber = 0;
    
    public ScenarioService(Model model) {
        this.model = model;
    }
    
    @Override
    protected Task createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateMessage("Sending tasks to the consumers...");
                
                //send the scenario
                for (Consumer c : model.getScenario().getConsumers()) {
                    sendScenario(c);
                }
                
                                updateMessage("The Sceanrio is running, waiting for results");

                //send start command
                ArrayList<StartThread> threads = new ArrayList<>();
                model.getScenario().getConsumers().stream().map((c) -> new StartThread(c)).map((s) -> {
                    threads.add(s);
                    return s;
                }).forEach((s) -> {
                    s.start();
                });
                for(Thread t : threads){
                    t.join();
                }
                updateMessage("Retrieving the scenario results");
                // retrieve the result
                for (Consumer c : model.getScenario().getConsumers()) {
                    sendRetrieveResult(c);
                }
                updateMessage("Emulation completed successfully");
                return null;
            }
        };
    }

    /**
     * Send scenario to a consumer
     */
    private void sendScenario(Consumer c) throws MalformedURLException {
        //get the scenario to send
        ArrayList<ConsumerTask> tasks = model.getConsumerTasks(c);
        //TODO : call setScenario service from the consumer service
        System.err.println("task size="+tasks.size());
        setScenario(tasks, c);
    }
    
    private void sendRetrieveResult(Consumer c) throws MalformedURLException {
        //TODO invoque the retrieve result service and store the result in the model
        ArrayList<TaskResult> results = (ArrayList<TaskResult>) retrieveResults(c);
        
        if (results.size() != model.getScenario().getTasks().size()) {
            System.err.println("We have a big problem "+results.size());
        }
        
        for (int i = 0; i < results.size(); i++) {
            //request time
            TaskResult r = results.get(i);
            
            ArrayList<Double> requestTimes = new ArrayList<>();
            ArrayList<Double> responseTimes = new ArrayList<>();
            ArrayList<Double> rttTimes = new ArrayList<>();
            
            System.out.println(r.getTimeToReceive());
            r.getTimeToSend().stream().filter((time) -> (time > 0)).forEach((time) -> {
                requestTimes.add((double) time);
            });
            
            r.getTimeToReceive().stream().filter((time) -> (time > 0)).forEach((time) -> {
                responseTimes.add((double) time);
            });
            
            for (int j = 0; j < r.getTimeToSend().size(); j++) {
                if (r.getTimeToSend().get(i) > 0) {
                    rttTimes.add((double) (r.getTimeToReceive().get(j) + r.getTimeToSend().get(j)));
                }
            }
            
            DescriptiveStatistics dRequestTime = new DescriptiveStatistics(toArray(requestTimes));
            DescriptiveStatistics dRespTime = new DescriptiveStatistics(toArray(responseTimes));
            DescriptiveStatistics dRttTime = new DescriptiveStatistics(toArray(rttTimes));
            
            KPI kpi = new KPI();
            kpi.setRequestTimeDist(new Distribution(dRequestTime));
            kpi.setResponseTimeDist(new Distribution(dRespTime));
            kpi.setRttDist(new Distribution(dRttTime));
            kpi.setNumberOfLoss(r.getTimeToSend().size() - requestTimes.size());
            kpi.setNumberOfNonLoss(requestTimes.size());
            model.getScenario().getTasks().get(i).setResult(kpi);
            
        }
    }
    
    private static boolean setScenario(java.util.List<ConsumerTask> tasks, Consumer c) throws MalformedURLException {
        org.insa.tasks.ConsumerWSService service = new org.insa.tasks.ConsumerWSService(c.getURL(), Consumer.getQname());
        org.insa.tasks.ConsumerWS port = service.getConsumerWSPort();
        return port.setScenario(tasks);
    }
    
    private static java.util.List<TaskResult> retrieveResults(Consumer c) throws MalformedURLException {
        org.insa.tasks.ConsumerWSService service = new org.insa.tasks.ConsumerWSService(c.getURL(), Consumer.getQname());
        org.insa.tasks.ConsumerWS port = service.getConsumerWSPort();
        return port.retrieveResults();
    }
    
    private double[] toArray(ArrayList<Double> list) {
        double[] res = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public Model getModel() {
        return model;
    }
    
}
