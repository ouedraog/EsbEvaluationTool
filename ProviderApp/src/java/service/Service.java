/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author insa
 */
@WebService()
public class Service {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "operation")
    public String operation(String request, int dataSize, int processingTime) {
        //the data to return
        String responseData = null;
        //the producer first timestamp (request reception time)
        long startTime;
        //the producer last timestamp (response sent time)
        long endTime;

        startTime = System.currentTimeMillis();
        //wait for the processing time
        waitFor(processingTime);
        //produce the data
        responseData = produceDate(dataSize);
        endTime = System.currentTimeMillis();
        //send the response
        String response = "("+startTime+","+endTime+")"+responseData;
        //System.out.println("response = "+response);
        return response;
    }

    private void waitFor(int timems) {
        try {
            Thread.sleep(timems);
        } catch (InterruptedException ex) {
        }
    }

    private String produceDate(int size){
        String data = "";
        for(int i=0; i<size; i++){
            data+="a";
        }
        return data;
    }
}

