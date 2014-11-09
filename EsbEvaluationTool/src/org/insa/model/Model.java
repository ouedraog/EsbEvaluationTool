package org.insa.model;
import java.util.ArrayList;
import java.util.Arrays;
import org.simpleframework.xml.Element;
/**
 * Class Model
 * @author Yahya Albaba, Deepali Jain, Marc Orfila, Juste Abel Ouedraogo, Vansh Pahwa
 */
public class Model {
    /**
     * The result
     */
    @Element
    private Result result;
  
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
    
    
    public Result getResult() {
        return result;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

}
