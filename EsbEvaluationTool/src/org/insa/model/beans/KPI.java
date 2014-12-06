package org.insa.model.beans;

import org.insa.model.beans.Distribution;
import org.simpleframework.xml.Element;

/**
 * Class Result
 * @author Yahya Albaba, Deepali Jain, Marc Orfila, Juste Abel Ouedraogo, Vansh Pahwa
 */
public class KPI {

    /**
     * The distribution of the response time
     */
     @Element
     private Distribution responseTimeDist;
     
     /**
      * The distribution of the request time
      */
     @Element
     private Distribution requestTimeDist;
     
     /**
      * The RTT
      */
     @Element
     private Distribution rttDist;

     /**
      * The distribution of the bus reliability
      */
     @Element
    private Distribution busReliabilityDist;

    public KPI() {
    }

    public Distribution getLossDist() {
        return busReliabilityDist;
    }




    public Distribution getResponseTimeDist() {
        return responseTimeDist;
    }

    public void setLossDist(Distribution busReliabilityDist) {
        this.busReliabilityDist = busReliabilityDist;
    }


    public void setResponseTimeDist(Distribution responseTimeDist) {
        this.responseTimeDist = responseTimeDist;
    }

    public Distribution getRequestTimeDist() {
        return requestTimeDist;
    }

    public void setRequestTimeDist(Distribution requestTimeDist) {
        this.requestTimeDist = requestTimeDist;
    }

    public Distribution getRttDist() {
        return rttDist;
    }

    public void setRttDist(Distribution rttDist) {
        this.rttDist = rttDist;
    }
    
}
