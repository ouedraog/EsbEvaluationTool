/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenario.model;

public class MessageResult {
    /**
     * Time taken by a request to reach the provider
     * Consumer --> Provider
     */
    private long requestTime;
    /**
     * Time taken by a response to reach the consumer
     * provider --> Consumer
     */
    private long responseTime;

    public MessageResult() {
        requestTime = -1;
        responseTime = -1;
    }

    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }
    
    

}
