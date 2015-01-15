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
        //the producer first timestamp (request reception time)
        long startTime = System.currentTimeMillis();
        System.out.println("request received at " + startTime + " : response size = " + dataSize + ", processing time = " + processingTime);
        //wait for the processing time
        waitFor(processingTime);
        //produce the data
        String responseData = produceMessage(dataSize);
        //the producer last timestamp (response sent time)
        long endTime = System.currentTimeMillis();
        //send the response
        String response = "(" + startTime + "," + endTime + ")" + responseData;
        return response;
    }

    private void waitFor(int timems) {
        try {
            Thread.sleep(timems);
        } catch (InterruptedException ex) {
        }
    }

    private String produceMessage(int size) {
        String data = "";
        for (int i = 0; i < size; i++) {
            data += "a";
        }
        return data;
    }
}
