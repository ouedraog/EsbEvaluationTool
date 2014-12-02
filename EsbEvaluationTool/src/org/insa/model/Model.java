package org.insa.model;
import java.util.ArrayList;
import java.util.Arrays;
import org.insa.model.beans.Consumer;
import org.insa.model.beans.Task;
import org.simpleframework.xml.Element;
/**
 * Class Model
 * @author Yahya Albaba, Deepali Jain, Marc Orfila, Juste Abel Ouedraogo, Vansh Pahwa
 */
public class Model {
  
    /**
     * The scenario
     */
    @Element
    private Scenario scenario;
    
    private String selectedEsb;
    
    public Model() {  
        scenario = new Scenario();
    }

    public String getSelectedEsb() {
        return selectedEsb;
    }

    public void setSelectedEsb(String selectedEsb) {
        this.selectedEsb = selectedEsb;
    }
    

    public Scenario getScenario() {
        return scenario;
    }
    
    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }
    
    /**
     * Get a consumer tasks
     * @param c
     * @return 
     */
    public ArrayList<Task> getConsumerTask(Consumer c){
        ArrayList<Task> result = new ArrayList<>();
        scenario.getTasks().stream().filter((t) -> (t.getConsumer().equals(c))).forEach((t) -> {
            result.add(t);
        });
        return result;
    }
}
