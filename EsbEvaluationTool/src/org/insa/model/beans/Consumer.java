/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.model.beans;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import org.simpleframework.xml.Attribute;

/**
 *
 * @author JusteAbel
 */
public class Consumer {

    @Attribute
    private String name;
    @Attribute
    private String wsdlAdress;
    public Consumer() {
    }

    public Consumer(String name, String location) {
        this.name = name;
        this.wsdlAdress = location;
    }

    public String getWsdlAdress() {
        return wsdlAdress;
    }

    public void setWsdlAdress(String wsdlAdress) {
        this.wsdlAdress = wsdlAdress;
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

    @Override
    public boolean equals(Object o) {
        if(o==null || ! (o instanceof Consumer)){
            return false;
        }
        Consumer other = (Consumer)o;
        return this.name.equals(other.getName()) && this.wsdlAdress.equals(other.getWsdlAdress());
    }
    
    public static QName getQname(){
        return new QName("http://service/", "ConsumerWSService");
    }
    
    public URL getURL() throws MalformedURLException{
        return new URL(wsdlAdress);
    }
}
