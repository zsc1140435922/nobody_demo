package com.zsc.example.nobody.thred;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class PrintDemo {

   private final Lock queueLock = new ReentrantLock();

   public void  print() {
      queueLock.lock();
      try {
         Long duration = (long) (Math.random() * 10000);
         System.out.println(Thread.currentThread().getName() 
            + "  Time Taken " + (duration / 1000) + " seconds.");
         Thread.sleep(duration);
      } catch (InterruptedException e) {
         e.printStackTrace();
      } finally {
         System.out.printf("%s printed the document successfully.\n", Thread.currentThread().getName());
         queueLock.unlock();
      }
   }
}

class ThreadDemo extends Thread {
   PrintDemo  printDemo;

   ThreadDemo( String name,  PrintDemo printDemo) {
      super(name);
      this.printDemo = printDemo;
   }   

   @Override
   public void run() {
      System.out.printf("%s starts printing a document\n", Thread.currentThread().getName());
      printDemo.print();
   }
}

public class TestThread8 {

   public static void main(String args[]) {
      PrintDemo PD = new PrintDemo();

      ThreadDemo t1 = new ThreadDemo( "Thread - 1 ", PD );
      ThreadDemo t2 = new ThreadDemo( "Thread - 2 ", PD );
      ThreadDemo t3 = new ThreadDemo( "Thread - 3 ", PD );
      ThreadDemo t4 = new ThreadDemo( "Thread - 4 ", PD );

      t1.start();
      t2.start();
      t3.start();
      t4.start();
   }
}

/***
 * public void lock()	获得锁
2	public void lockInterruptibly()	获取锁定，除非当前线程中断
3	public Condition newCondition()	返回绑定到此Lock实例的新Condition实例
4	public boolean tryLock()	只有在调用时才可以获得锁
5	public boolean tryLock(long time, TimeUnit unit)	如果在给定的等待时间内自由，并且当前线程未被中断，则获取该锁。
6	public void unlock()	释放锁
*ReentrantLock类作为Lock接口的一个实现。 ReentrantLock类允许线程锁定方法，即使它已经具有其他方法锁。
*
**
*/
