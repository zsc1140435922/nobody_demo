package com.zsc.example.nobody.thred;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * 在将来的某个时间执行给定的命令。
 * @author Administrator
 *
 */
public class TestThread3 {

   public static void main(final String[] arguments) throws InterruptedException {
      Executor executor = Executors.newCachedThreadPool();
      executor.execute(new Task());
      ThreadPoolExecutor pool = (ThreadPoolExecutor)executor;
      pool.shutdown();
   }  

   static class Task implements Runnable {
      public void run() {
         try {
        	Date data = new Date();
            Long duration = (long) (Math.random() * 5);
            System.out.println("Running Task!");
            TimeUnit.SECONDS.sleep(duration); 
            Date data1 = new Date();
            System.out.println("duration="+duration);
            System.out.println(data1.getTime() - data.getTime());
            System.out.println("Task Completed");
         } 
         catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }
}

