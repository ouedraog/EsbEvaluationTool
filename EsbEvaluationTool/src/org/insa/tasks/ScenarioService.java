/*
 * Service for executing the scenario deployment and results retrieving tasks
 */
package org.insa.tasks;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.insa.model.Model;
import org.insa.model.beans.Consumer;

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

                //send the scenario
                for (Consumer c : model.getScenario().getConsumers()) {
                    sendScenario(c);
                }

                //send start command
                double start = System.currentTimeMillis();
                for (Consumer c : model.getScenario().getConsumers()) {
                    StartThread s = new StartThread(c);
                    s.start();
                    s.join();

                }
                
                // retrieve the result
                for (Consumer c : model.getScenario().getConsumers()){
                    sendRetrieveResult(c);
                }
                return null;
            }
        };
    }

    /**
     * Send scenario to a consumer
     */
    private void sendScenario(Consumer c) {
        //get the scenario to send
        ArrayList<org.insa.model.beans.Task> tasks = model.getConsumerTask(c);

        //TODO : call setScenario service from the consumer service
    }
    
    private void sendRetrieveResult(Consumer c){
        //TODO invoque the retrieve result service and store the result in the model
    }

}
