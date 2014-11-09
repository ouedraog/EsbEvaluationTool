/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.model.beans;

import org.simpleframework.xml.Attribute;

/**
 *
 * @author JusteAbel
 */
public class Consumer {

    @Attribute
    private String name;
    @Attribute
    private String location;
    public Consumer() {
    }

    public Consumer(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
