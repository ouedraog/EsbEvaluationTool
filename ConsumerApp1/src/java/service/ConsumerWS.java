/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

//import task.ConsumerResult;
import task.TaskExecutor;
import task.ConsumerTask;
import task.TaskResult;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebService;

import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
//import task.ConsumerTask;

/**
 *
 * @author insa
 */
@WebService()
public class ConsumerWS {

    private ArrayList<ConsumerTask> tasks;

    // maps consumerresults to message id;
    private ArrayList<TaskResult> results;

    public ConsumerWS() {

        this.tasks = new ArrayList<>();
        this.results = new ArrayList<>();
    }

    /**
     * Web service for getting the scenario to run
     *
     * @param tasks
     * @return
     */
    @WebMethod(operationName = "setScenario")
    public boolean setScenario(ArrayList<ConsumerTask> tasks) {
        System.out.println("Scenario received");
        this.tasks = tasks;
        this.results = new ArrayList<>();
        return true;

    }

    /**
     *
     * @param tasks the list of tasks
     */
    private void executeTasks(ArrayList<ConsumerTask> tasks) throws InterruptedException {
        ArrayList<TaskExecutor> executors = new ArrayList<>();
        for (ConsumerTask task : tasks) {
            TaskExecutor t = new TaskExecutor(task);
            executors.add(t);
            t.start();
        }

        for (TaskExecutor exc : executors) {
            exc.join();
        }
        for (TaskExecutor exc : executors) {
            results.add(exc.getResult());
        }

        System.err.println("Finished scenario");
    }

    /**
     * Web service for starting the scenario
     *
     * @return
     */
    @WebMethod(operationName = "startScenario")
    public boolean startScenario() throws InterruptedException {
        System.out.println("Scenario started");
        if (this.tasks.isEmpty()) {
            return false;
        };

        executeTasks(tasks);

        System.out.println("Finished");
        return true;

    }

    /**
     * Web service for retrieving the results
     *
     * @return
     */
    @WebMethod(operationName = "retrieveResults")
    public ArrayList<TaskResult> retrieveResults() {
        System.out.println("Retrieving results");
        return results;
    }

}
