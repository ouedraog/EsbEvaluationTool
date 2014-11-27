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
public class Ttask
{
    public Integer total_time  ;//time in milliseconds
    public Integer time_period ;//time per request 

   
    public Integer producer_id ;
    public Integer consumer_id ;
    public Integer processing_time ;
    public Integer csrequest_size ; //requestsize in bytes
    public Integer prrequest_size ; 
     
    public String configmsg(int size)
    {
        Msg toreturn = null;
        String x =null;
        for(int i=1;i<(size);i++)
        x = x +'a';
        return x;
    }
    
    public Msg config(int size,int requestid) 
    {   
        String x= configmsg(size-36);
        Msg toreturn = new Msg();
        toreturn.setMsg(x);
        toreturn.setConsumerid(consumer_id);
        toreturn.setProducerid(producer_id);
        toreturn.setProducermsgsize(prrequest_size);
        toreturn.setConsumermsgsize(csrequest_size);
        toreturn.setRequestid(requestid);
        toreturn.setRequesttime(processing_time);
        return toreturn;
    }
     public Msg consumerconfig(int requestid) 
    {   
        Msg toreturn =config(csrequest_size,requestid);
        toreturn.setConsumer2producer(false);
        return toreturn;
    }
    
     public Msg producerconfig(int requestid,int[] timestamps ) 
    {   
         Msg toreturn =config(prrequest_size,requestid);
         toreturn.setConsumer2producer(false);
      return toreturn;
    }
   
 }
