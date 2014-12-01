/*
 * Service for executing the scenario deployment and results retrieving tasks
 */
package org.insa.tasks;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.insa.model.Model;
/**
 *
 * @author JusteAbel
 */
public class ScenarioService extends Service<Void>{

    /**
     * The model
     */
    private Model model;
    
    public ScenarioService(Model model){
        this.model = model;
    }
    @Override
    protected Task createTask() {
        return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    
                    //TODO : put the logic of the scenario deployment and retrieve results here 
                    
                    Thread.sleep(30000);
                    return null;
                }
            };
    }
    
}
