/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task;

   public class Message
{
    private String msg =new String();
    private int requesttime;
    private long sendingTime=-1 ;//when msg is sent from consumer
    private long receptionTime=-1 ;//when msg is sent by producer


    public long getSendingTime() {
        return sendingTime;
    }

    public void setSendingTime(long timestamp1) {
        this.sendingTime = timestamp1;
    }


    public long getReceptionTime() {
        return receptionTime;
    }

    public void setReceptionTime(long timestamp4) {
        this.receptionTime = timestamp4;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg =new String() ;
        this.msg =msg;
    }

    public int getRequesttime() {
        return requesttime;
    }

    public void setRequesttime(int requesttime) {
        this.requesttime = requesttime;
    }
    
    
 }
