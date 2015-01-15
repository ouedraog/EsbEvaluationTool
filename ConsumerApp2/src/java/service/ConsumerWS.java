/**
 *
 * This webservice allows the consumer application to get a scenario from
 * the main application (launcher) and send it back the results of the emulation
 * Services used:
 * setScenario : gets a scenario from the launcher
 * startScenario : starts the emulation
 * retrieveResults : retrieves the results of the emulation
 */
package service;

import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebService;
import scenario.model.ConsumerTask;
import scenario.ScenarioManager;
import scenario.model.TaskResult;


@WebService()
public class ConsumerWS {
    
    /**
     * The scenario manager
     */
    private ScenarioManager scenarioManager;

    public ConsumerWS() {
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
        scenarioManager = new ScenarioManager();
        scenarioManager.setTasks(tasks);
        return true;

    }
    /**
     * Web service for starting the scenario
     *
     * @return false if failure otherwise true
     */
    @WebMethod(operationName = "startScenario")
    public boolean startScenario() {
        System.out.println("Scenario started");
        //exit if no valid scenario has been provided
        if (!scenarioManager.hasScenario()) {
            return false;
        }

        scenarioManager.runScenario();

        System.out.println("Scenario complete");
        return true;

    }

    /**
     * Web service for retrieving the results
     *
     * @return the results
     */
    @WebMethod(operationName = "retrieveResults")
    public ArrayList<TaskResult> retrieveResults() {
        System.out.println("Retrieving results");
        return scenarioManager.getResults();
    }

}
