package org.insa.model;

import java.util.ArrayList;
import org.apache.commons.math3.stat.StatUtils;
import org.insa.model.beans.KPI;
import org.insa.model.beans.KPI;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

/**
 * Class Result
 * @author Yahya Albaba, Deepali Jain, Marc Orfila, Juste Abel Ouedraogo, Vansh Pahwa
 */
public class Result {

    /**
     * The distribution of the response time
     */
     @Element
     private KPI responseTimeDist;

     /**
      * The distribution of the bus reliability
      */
     @Element
    private KPI busReliabilityDist;

    public Result() {
    }

    public KPI getBusReliabilityDist() {
        return busReliabilityDist;
    }




    public KPI getResponseTimeDist() {
        return responseTimeDist;
    }

    public void setBusReliabilityDist(KPI busReliabilityDist) {
        this.busReliabilityDist = busReliabilityDist;
    }


    public void setResponseTimeDist(KPI responseTimeDist) {
        this.responseTimeDist = responseTimeDist;
    }
}
