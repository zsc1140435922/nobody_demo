package com.zsc.example.nobody.thred.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestThread4 {

   public static void main(final String[] arguments) throws InterruptedException {

      ExecutorService executor = Executors.newSingleThreadExecutor();

      try {
         executor.submit(new Task());
         System.out.println("Shutdown executor");
         executor.shutdown();
         System.out.println("1");
         executor.awaitTermination(5, TimeUnit.SECONDS);
      }
      catch (InterruptedException e) {
         System.err.println("tasks interrupted");
      }
      finally {
         if (!executor.isTerminated()) {
            System.err.println("cancel non-finished tasks");
         }
         executor.shutdownNow();
         System.out.println("shutdown finished");
      }
   }	

   static class Task implements Runnable {
      public void run() {
         try {
            Long duration = (long) (Math.random() * 20);
            System.out.println("Running Task!");
            System.out.println("duration START="+duration);
            TimeUnit.SECONDS.sleep(duration);
            System.out.println("duration="+duration);
         } 
         catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }       
}
/**
 * boolean awaitTermination(long timeout, TimeUnit unit) 	阻止所有任务在关闭请求完成后执行，或发生超时，或当前线程中断，以先到者为准。
 * boolean isShutdown() 	如果执行程序已关闭，则返回true。
 * boolean isTerminated() 	如果所有任务在关闭后完成，则返回true。
 * void shutdown() 	启动有序关闭，其中先前提交的任务将被执行，但不会接受任何新任务。
 * List<Runnable> shutdownNow() 	尝试停止所有主动执行的任务，停止等待任务的处理，并返回正在等待执行的任务列表。
 * 
 * 
 */
