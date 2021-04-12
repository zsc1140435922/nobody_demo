package com.zsc.example.nobody.thred.executorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TestThread5 {

   public static void main(final String[] arguments) throws InterruptedException {
      final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);//方法获得一个调度的线程池。
     // scheduler.schedule(new BeepTask(), 5, TimeUnit.SECONDS);

      final ScheduledFuture<?> beepHandler = 
         scheduler.scheduleAtFixedRate(new BeepTask(), 0, 2, TimeUnit.SECONDS);

//      scheduler.schedule(new Runnable() {
//         @Override
//         public void run() {
//        	 System.out.println(1);
//            beepHandler.cancel(true);
//            scheduler.shutdown();   
//            System.out.println(2);
//         }
//      }, 6, TimeUnit.SECONDS);
   }  

   static class BeepTask implements Runnable {
      public void run() {
    	  for(int i=0;i<10000;i++){
    		  System.out.println("beep "+i);      
    	  }
      }
   }
}
/***
 * ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)
 * 创建并执行在给定的初始延迟之后，随后以给定的时间段首先启用的周期性动作; 那就是执行会在initialDelay之后开始，然后是initialDelay + period，然后是initialDelay + 2 * period，等等。
 *
 * ScheduledFuture schedule(Callable callable, long delay, TimeUnit unit) 	创建并执行在给定延迟后启用ScheduledFuture。
 * ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) 	创建并执行在给定延迟后启用的单次操作。
 * 
 * 
**/