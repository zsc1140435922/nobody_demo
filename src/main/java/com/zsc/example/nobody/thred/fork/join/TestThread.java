package com.zsc.example.nobody.thred.fork.join;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
/**
 * fork-join框架允许在几个工作进程中断某个任务，然后等待结果组合它们。 它在很大程度上利用了多处理器机器的生产能力
 * 
 * Join
连接(Join)是子任务完成执行后任务加入子任务的所有结果的过程，否则它会持续等待。
语法
left.join();
这里剩下的是Sum类的一个对象。
 * @author Administrator
 *
 */
public class TestThread {

   public static void main(final String[] arguments) throws InterruptedException, ExecutionException {
      int nThreads = Runtime.getRuntime().availableProcessors();
      System.out.println(nThreads);

      int[] numbers = new int[1000]; 

      for(int i=0; i< numbers.length; i++){
         numbers[i] = i;
      }

      ForkJoinPool forkJoinPool = new ForkJoinPool(nThreads);
      Long result = forkJoinPool.invoke(new Sum(numbers,0,numbers.length));
      System.out.println(result);
   }  

   @SuppressWarnings("serial")
static class Sum extends RecursiveTask<Long> {

      int low;
      int high;
      int[] array;

      Sum(int[] array, int low, int high) {
         this.array = array;
         this.low   = low;
         this.high  = high;
      }

      protected Long compute() {
         if(high - low <= 10) {
            long sum = 0;
            for(int i=low; i < high; ++i) 
               sum += array[i];
               return sum;
         } else {            
            int mid = low + (high - low) / 2;
            Sum left  = new Sum(array, low, mid);
            Sum right = new Sum(array, mid, high);
            left.fork();//Fork是一个进程，其中任务将其分成可以并发执行的较小且独立的子任务。
            long rightResult = right.compute();
            long leftResult  = left.join();//连接(Join)是子任务完成执行后任务加入子任务的所有结果的过程，否则它会持续等待
            return leftResult + rightResult;
         }
      }
   }
}
