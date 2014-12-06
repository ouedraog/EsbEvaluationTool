/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.model;

import java.util.ArrayList;
import org.insa.model.beans.*;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

/**
 *
 * @author JusteAbel
 */
public class Scenario {

    @Attribute
    private String name;
    /**
     * The producer list
     */
    @ElementList(name = "producers")
    private ArrayList<Producer> producers;

    /**
     * The consumer list
     */
    @ElementList(name = "consumers")
    private ArrayList<Consumer> consumers;

    /**
     * All the links between consumers and producers
     */
    @ElementList
    private ArrayList<Task> tasks;

    public Scenario() {
        this.consumers = new ArrayList<>();
        this.producers = new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.name = "Scenario";
    }

    /*------------------- helpers -----------------------*/
    /**
     * Add link between producer and consumer
     *
     * @param link
     */
    public void addTask(Task link) {
        this.tasks.add(link);
    }

    /**
     * Add a consumer to the list
     *
     * @param consumer
     */
    public void addConsumer(Consumer consumer) {
        this.consumers.add(consumer);
    }

    /**
     * Add a producer to the list
     *
     * @param producer
     */
    public void addProducer(Producer producer) {
        this.producers.add(producer);
    }
    /*---------------------- getters and setters --------------------*/

    public ArrayList<Consumer> getConsumers() {
        return consumers;
    }

    public void setConsumers(ArrayList<Consumer> consumers) {
        this.consumers = consumers;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setLinks(ArrayList<Task> links) {
        this.tasks = links;
    }

    public ArrayList<Producer> getProducers() {
        return producers;
    }

    public void setProducers(ArrayList<Producer> producers) {
        this.producers = producers;
    }

    @Override
    public String toString() {
        return getName();
    }

    public String printScenario() {
        String str = "Scenario\n\tproducers = " + producers;
        str += "\n\tconsumers = " + consumers;
        str += "\n\tlinks = [";
        for (Task l : tasks) {
            str += "\n\t\t" + l;
        }
        str += "\n\t\t]";
        return str;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /* --------------- The result ------------------*/
    public int getNumberOfLoss() {
        return 1;
    }

    public int getNumberOfNonLoss() {
        return 10;
    }

    public int getNumberOfTask() {
        return tasks.size();
    }

    public double getMeanRequestTime() {
        double mean = 0;
        mean = tasks.stream().map((t) -> t.getResult().getRequestTimeDist().getAverage()).reduce(mean, (accumulator, _item) -> accumulator + _item);
        mean /= getNumberOfTask();
        return mean;
    }

    public double getStdevRequestTime() {
        double mean = 0;
        mean = tasks.stream().map((t) -> t.getResult().getRequestTimeDist().getStdev()).reduce(mean, (accumulator, _item) -> accumulator + _item);
        mean /= getNumberOfTask();
        return mean;
    }

    public double getMeanResponseTime() {
        double mean = 0;
        mean = tasks.stream().map((t) -> t.getResult().getResponseTimeDist().getAverage()).reduce(mean, (accumulator, _item) -> accumulator + _item);
        mean /= getNumberOfTask();
        return mean;
    }

    public double getStdevResponseTime() {
        double mean = 0;
        mean = tasks.stream().map((t) -> t.getResult().getResponseTimeDist().getStdev()).reduce(mean, (accumulator, _item) -> accumulator + _item);
        mean /= getNumberOfTask();
        return mean;
    }

    public double getMeanRTT() {
        double mean = 0;
        mean = tasks.stream().map((t) -> t.getResult().getRttDist().getAverage()).reduce(mean, (accumulator, _item) -> accumulator + _item);
        mean /= getNumberOfTask();
        return mean;
    }

    public double getStdevRTT() {
        double mean = 0;
        mean = tasks.stream().map((t) -> t.getResult().getRttDist().getStdev()).reduce(mean, (accumulator, _item) -> accumulator + _item);
        mean /= getNumberOfTask();
        return mean;
    }
    
}
