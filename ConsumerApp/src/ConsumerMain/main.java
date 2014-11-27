/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConsumerMain;

import Launcher.Ttask;

 /*
 * @author vidhipahwa
 */
public class main {
       
    public static void main(String[] args) {
        Ttask ttask =new Ttask();
        ttask.total_time=10000;
        ttask.time_period=50 ;
        Consumer.sendTask(ttask);
    }
    
}
