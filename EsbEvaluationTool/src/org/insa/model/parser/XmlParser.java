/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.model.parser;

import java.io.File;
import org.insa.model.Model;
import org.insa.model.beans.KPI;
import org.insa.model.Scenario;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.CycleStrategy;
import org.simpleframework.xml.strategy.Strategy;

/**
 *
 * @author JusteAbel
 */
public class XmlParser {

    /**
     * Read a scenario file
     *
     * @param input the input file
     * @return the scenario object
     * @throws java.lang.Exception
     */
    public static Scenario readScenarioFile(File input) throws Exception {
        Strategy strategy = new CycleStrategy("id", "ref");
        Serializer serializer = new Persister(strategy);
        return serializer.read(Scenario.class, input);
    }

    /**
     * Save a scenario to xml file
     *
     * @param scenario the scenario to save
     * @param output the output file
     * @throws Exception
     */
    public static void saveScenario(Scenario scenario, File output) throws Exception {
        Strategy strategy = new CycleStrategy("id", "ref");
        Serializer serializer = new Persister(strategy);
        serializer.write(scenario, output);
    }

    /**
     * Save the result in a file
     * Save also the scenario which goes with
     */
    public static void saveResult(Model model, File output) throws Exception{
        Strategy strategy = new CycleStrategy("id", "ref");
        Serializer serializer = new Persister(strategy);
        serializer.write(model, output);
    }

     /**
     * Read a result file
     *
     * @param input the input file
     * @return the scenario object
     * @throws java.lang.Exception
     */
    public static Model readResultFile(File input) throws Exception {
        Strategy strategy = new CycleStrategy("id", "ref");
        Serializer serializer = new Persister(strategy);
        return serializer.read(Model.class, input);
    }
}
