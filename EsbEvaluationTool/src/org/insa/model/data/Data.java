/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.model.data;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.insa.model.Scenario;
import org.insa.model.beans.Consumer;
import org.insa.model.beans.Task;
import org.insa.model.beans.Producer;
import org.insa.model.parser.XmlParser;

/**
 *
 * @author JusteAbel
 */
public class Data {

    private final ArrayList<Producer> producers;
    private final ArrayList<Consumer> consumers;
    private final int defaultProcessingTime = 100;
    private final int defaultRequestSize = 100;
    private final int defaultResponseSize = 200;
    private final int defaultRequestFreq = 200;
    private final ArrayList<String> esbList;

    private int numberOfTasks = 4;

    public Data() {
        producers = new ArrayList<>();
        consumers = new ArrayList<>();

        producers.add(new Producer("Provider 1", "http://192.168.43.241:9080/BridgeProtocoleService1/esbProvider?WSDL"));

        consumers.add(new Consumer("Consumer 1", "http://localhost:8080/ConsumerApp1/ConsumerWSService?WSDL"));

        consumers.add(new Consumer("Consumer 2", "http://localhost:8080/ConsumerApp2/ConsumerWSService?WSDL"));

        String allESB[] = {"OpenESB"};
        esbList = new ArrayList<>(Arrays.asList(allESB));
    }

    /**
     * @param processingTime
     * @return
     */
    public Scenario processingTimeScenario(int processingTime) {
        Scenario s = new Scenario();
        s.setName("Processing Time Scenario");
        Consumer c = getConsumers().get(0);
        Producer p = getProducers().get(0);

        s.addConsumer(c);
        s.addProducer(p);
        for (int i = 1; i < numberOfTasks + 1; i++) {
            s.addTask(new Task(p, c, defaultRequestSize, defaultResponseSize, defaultRequestFreq, processingTime * i, defaultRequestFreq * 10));
        }
        return s;
    }

    public Scenario dataSizeScenario(int requestSize, int responseTime) {
        Scenario s = new Scenario();
        s.setName("Data Size Scenario");
        Consumer c = getConsumers().get(0);
        Producer p = getProducers().get(0);
        s.addConsumer(c);
        s.addProducer(p);
        for (int i = 1; i < numberOfTasks + 1; i++) {
            s.addTask(new Task(p, c, requestSize * i, responseTime * i, defaultRequestFreq, defaultProcessingTime, defaultRequestFreq * 20));
        }
        return s;
    }

    public Scenario requestFrequencyScenario(int requestFrequency) {
        Scenario s = new Scenario();
        s.setName("Request Frequency Scenario");
        Consumer c = getConsumers().get(0);
        Producer p = getProducers().get(0);
        s.addConsumer(c);
        s.addProducer(p);
        for (int i = 1; i < numberOfTasks + 1; i++) {
            int freq = requestFrequency * i;
            s.addTask(new Task(p, c, defaultRequestSize, defaultResponseSize, freq, defaultProcessingTime, freq * 50));
        }
        return s;
    }

    public ArrayList<Consumer> getConsumers() {
        return consumers;
    }

    public ArrayList<Producer> getProducers() {
        return producers;
    }

    public void createSampleScenario(File file) throws Exception {
        Scenario s = new Scenario();

        s.setName("Frequency scenario");
        Consumer c = getConsumers().get(0);
        Producer p = getProducers().get(0);
        s.addConsumer(c);
        s.addProducer(p);
        for (int i = 1; i < 51; i++) {
            s.addTask(new Task(p, c, 1, 1, i, 1, 70 * 50));
        }

        XmlParser.saveScenario(s, file);
    }

    public static void main(String args[]) {
        try {
            new Data().createSampleScenario(new File("data/Frequency.xml"));
            System.out.println("saved");
        } catch (Exception ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<String> getEsbList() {
        return esbList;
    }

    public void setNumberOfTasks(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }

}
