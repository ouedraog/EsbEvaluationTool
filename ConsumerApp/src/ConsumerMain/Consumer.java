/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConsumerMain;

import Launcher.Ttask;
import Launcher.Msg;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author vidhipahwa
 */
public class Consumer  {

    int nooftasks;
    int nettime;
    int consumerid;
    static int requestid = 1000;
    static Timer timer =new Timer();
    public Consumer(int id) {
        this.consumerid = id;
    }

 

    public static void sendMsg(Ttask task) {
        Msg toSend = task.consumerconfig(requestid);
        toSend.setTimestamp1(System.currentTimeMillis());
        System.out.println("Message"+ requestid + "sent");//instead of this send the message actually
        
        requestid++;
    }

    private static class Send extends TimerTask
    {

        private static Ttask task1;
        int counter;
        public Send(Ttask task,int counter) {
            task1 = task;
            this.counter=counter;
        }

        @Override
        public void run() {
             sendMsg(task1);
            if (requestid>=counter+(task1.total_time/task1.time_period))
            timer.cancel();
        }
    }

    public static void sendTask(Ttask task)
    {
        int counter=requestid;
        timer.scheduleAtFixedRate(new Send(task,counter), 0, task.time_period);
        
    }
    
    /**
     *
     * @param b the list of tasks
     */
    public void configure(ArrayList<Ttask> b)    {
         this.nooftasks = b.size();
         for (int i=0;i<nooftasks;i++)
         {
             sendTask(b.get(i));
         }
    }
    


}
