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
    private ArrayList<Partnership> links;

    public Scenario() {
        this.consumers = new ArrayList<Consumer>();
        this.producers = new ArrayList<Producer>();
        this.links = new ArrayList<Partnership>();
        this.name = "Scenario";
    }

    /*------------------- helpers -----------------------*/
    /**
     * Add link between producer and consumer
     *
     * @param link
     */
    public void addLink(Partnership link) {
        this.links.add(link);
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

    public ArrayList<Partnership> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Partnership> links) {
        this.links = links;
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
        for (Partnership l : links) {
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
    
}
