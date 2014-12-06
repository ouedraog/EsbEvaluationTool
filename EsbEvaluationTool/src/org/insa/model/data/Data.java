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
   private  final int defaultProcessingTime = 500;
   private  final int defaultRequestSize = 100;
   private  final int defaultResponseSize = 200;
   private  final int defaultRequestFreq = 2;
   private final  ArrayList<String>esbList;
   
   private final int numberOfTasks = 10;
   
    public Data() {
        producers = new ArrayList<>();
        consumers = new ArrayList<>();
        
        producers.add(new Producer("Airport system", "localhost/airsystem?wsdl"));
        
        consumers.add(new Consumer("Aircraft system", "localhost/aircraft?wsdl"));
        consumers.add(new Consumer("Military base", "localhost/military?wsdl"));
        
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

        s.addConsumer(c);
        s.addProducer(p);
        for(int i=0; i<numberOfTasks; i++){
            s.addTask(new Task(p, c, defaultRequestSize, defaultResponseSize, defaultRequestFreq, processingTime*i));
        }
        return s;
    }
    public  Scenario dataSizeScenario(int requestSize, int responseTime){
        Scenario s = new Scenario();
        s.setName("Data Size Scenario");
        Consumer c =  getConsumers().get(0);
        Producer p = getProducers().get(0);
        s.addConsumer(c);
        s.addProducer(p);
        for(int i=0; i<numberOfTasks; i++){
            s.addTask(new Task(p, c, requestSize*i, responseTime*i, defaultRequestFreq, defaultProcessingTime));
        }
        return s;
    }
    public  Scenario requestFrequencyScenario(int requestFrequency){
        Scenario s = new Scenario();
        s.setName("Request Frequency Scenario");
        Consumer c =  getConsumers().get(0);
        Producer p = getProducers().get(0);
        s.addConsumer(c);
        s.addProducer(p);
        for(int i=0; i<numberOfTasks; i++){
            s.addTask(new Task(p, c, defaultRequestSize, defaultResponseSize, requestFrequency*i, defaultProcessingTime));
        }
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
        Consumer c1 =  getConsumers().get(1);
        Producer p = getProducers().get(0);
        s.addConsumer(c);
        s.addProducer(p);
        s.addConsumer(c1);
        s.addTask(new Task(p, c, 50, 60, 1, 20));
        s.addTask(new Task(p, c, 10, 200, 10, 20));
        s.addTask(new Task(p, c1, 20, 600, 10, 10));
        
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
