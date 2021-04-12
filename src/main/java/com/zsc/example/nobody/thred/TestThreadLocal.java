package com.zsc.example.nobody.thred;

import java.util.Random;

public class TestThreadLocal {

    public static class MyRunnable1 implements Runnable {  
  
        private ThreadLocal<Integer> threadlocal = new ThreadLocal<Integer>();  
  
        @Override  
        public void run() {  
            threadlocal.set(new Random().nextInt(10));  
            try {  
            	System.out.println(threadlocal.get());  
                Thread.sleep(2000);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
            System.out.println(Thread.currentThread() + " : " + threadlocal.get());  
        }  
    }  
  
    public static void main(String[] args) {  
        System.out.println("start");  
        MyRunnable1 runnable = new MyRunnable1();  
        Thread thread1 = new Thread(runnable);  
        Thread thread2 = new Thread(runnable);  
        thread1.start();  
        thread2.start();  
    }
}  
/**
 * 当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
 * 
 */