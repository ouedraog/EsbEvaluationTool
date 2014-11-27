/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Launcher;

/**
 *
 * @author vidhipahwa
 */
public class Msg
{
    private String msg =new String();
    private int requestid;
    private boolean consumer2producer ; // for msgs sent from consumer2producer it is true for reversemsgs it is false
    private int consumerid;
    private int producerid;
    private int requesttime;
    private long timestamp1=-1 ;
    private long timestamp2=-1 ;
    private long timestamp3=-1 ;
    private long timestamp4=-1 ;
    private long timestamp5=-1 ;

    public long getTimestamp3() {
        return timestamp3;
    }

    public void setTimestamp3(long timestamp3) {
        this.timestamp3 = timestamp3;
    }
   
    private int consumermsgsize ;
    private int producermsgsize ;

    public long getTimestamp1() {
        return timestamp1;
    }

    public void setTimestamp1(long timestamp1) {
        this.timestamp1 = timestamp1;
    }

    public long getTimestamp2() {
        return timestamp2;
    }

    public void setTimestamp2(long timestamp2) {
        this.timestamp2 = timestamp2;
    }

    public long getTimestamp4() {
        return timestamp4;
    }

    public void setTimestamp4(long timestamp4) {
        this.timestamp4 = timestamp4;
    }

    public long getTimestamp5() {
        return timestamp5;
    }

    public void setTimestamp5(long timestamp5) {
        this.timestamp5 = timestamp5;
    }
 
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg =new String() ;
        this.msg =msg;
    }

    public int getRequestid() {
        return requestid;
    }

    public void setRequestid(int requestid) {
        this.requestid = requestid;
    }

    public boolean isConsumer2producer() {
        return consumer2producer;
    }

    public void setConsumer2producer(boolean consumer2producer) {
        this.consumer2producer = consumer2producer;
    }

    public int getConsumerid() {
        return consumerid;
    }

    public void setConsumerid(int consumerid) {
        this.consumerid = consumerid;
    }

    public int getProducerid() {
        return producerid;
    }

    public void setProducerid(int producerid) {
        this.producerid = producerid;
    }

    public int getRequesttime() {
        return requesttime;
    }

    public void setRequesttime(int requesttime) {
        this.requesttime = requesttime;
    }

   

    public int getConsumermsgsize() {
        return consumermsgsize;
    }

    public void setConsumermsgsize(int consumermsgsize) {
        this.consumermsgsize = consumermsgsize;
    }

    public int getProducermsgsize() {
        return producermsgsize;
    }

    public void setProducermsgsize(int producermsgsize) {
        this.producermsgsize = producermsgsize;
    }
    
    
 }
