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
   private final   ArrayList<Producer> producers;
   private final   ArrayList<Consumer> consumers;
   private  final int defaultProcessingTime = 50;
   private  final int defaultRequestSize = 100;
   private  final int defaultResponseSize = 200;
   private  final int defaultRequestFreq = 2;
   private final  ArrayList<String>esbList;
    public Data() {
        producers = new ArrayList<>();
        consumers = new ArrayList<>();
        
        producers.add(new Producer("Airport system", "localhost/airsystem?wsdl"));
        consumers.add(new Consumer("Aircraft system", "localhost/aircraft?wsdl"));
        
        String allESB[] = {"OpenESB"};
        esbList = new ArrayList<>(Arrays.asList(allESB));
    }
    
   /**
     * @param processingTime
     * @return 
     */
    public  Scenario processingTimeScenario(int processingTime){
        Scenario s = new Scenario();
        s.setName("Processing Time Scenario");
        Consumer c =  getConsumers().get(0);
        Producer p = getProducers().get(0);
        Task l = new Task(p, c, defaultRequestSize, defaultResponseSize, defaultRequestFreq, processingTime);
        s.addConsumer(c);
        s.addProducer(p);
        s.addTask(l);
        return s;
    }
    public  Scenario dataSizeScenario(int requestSize, int responseTime){
        Scenario s = new Scenario();
        s.setName("Data Size Scenario");
        Consumer c =  getConsumers().get(0);
        Producer p = getProducers().get(0);
        Task l = new Task(p, c, requestSize, responseTime, defaultRequestFreq, defaultProcessingTime);
        s.addConsumer(c);
        s.addProducer(p);
        s.addTask(l);
        return s;
    }
    public  Scenario requestFrequencyScenario(int requestFrequency){
        Scenario s = new Scenario();
        s.setName("Request Frequency Scenario");
        Consumer c =  getConsumers().get(0);
        Producer p = getProducers().get(0);
        Task l = new Task(p, c, defaultRequestSize, defaultResponseSize, requestFrequency, defaultProcessingTime);
        s.addConsumer(c);
        s.addProducer(p);
        s.addTask(l);
        return s;
    }
    public  ArrayList<Consumer> getConsumers() {
        return consumers;
    }

    public  ArrayList<Producer> getProducers() {
        return producers;
    }
    
    public void createSampleScenario(File file) throws Exception{
        Scenario s = new Scenario();
        Consumer c =  getConsumers().get(0);
        Producer p = getProducers().get(0);
        s.addConsumer(c);
        s.addProducer(p);
        s.addTask(new Task(p, c, 50, 60, 1, 20));
        s.addTask(new Task(p, c, 10, 200, 10, 20));
        s.addTask(new Task(p, c, 20, 600, 10, 10));
        
        XmlParser.saveScenario(s, file);
    }
    
    public static void main(String args[]){
       try {
           new Data().createSampleScenario(new File("Scenario.xml"));
       } catch (Exception ex) {
           Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    public  ArrayList<String> getEsbList() {
        return esbList;
    }
    
    
}
