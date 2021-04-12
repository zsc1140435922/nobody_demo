package com.zsc.example.nobody.thred;
import java.util.concurrent.atomic.AtomicBoolean;

public class TestThread7 {

	public static void main(final String[] arguments) throws InterruptedException {
	      final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
	      new Thread("Thread 1") {
	         public void run() {
	            while(true){
	               System.out.println(Thread.currentThread().getName() 
	                  +" Waiting for Thread 2 to set Atomic variable to true. Current value is "
	                  + atomicBoolean.get());
	               if(atomicBoolean.compareAndSet(true, false)) {
	            	   System.out.println(atomicBoolean.get());
	                  System.out.println("Done!");
	                  break;
	               }
	            }};
	      }.start();

	      new Thread("Thread 2") {
	         public void run() {
	            System.out.println(Thread.currentThread().getName() + ", Atomic Variable: " +atomicBoolean.get()); 
	            System.out.println(Thread.currentThread().getName() +" is setting the variable to true ");
	            atomicBoolean.set(true);
	            System.out.println(Thread.currentThread().getName() + ", Atomic Variable: " +atomicBoolean.get()); 
	         };
	      }.start();
	   }  
	}


/**
 * 
 * public boolean compareAndSet(boolean expect, boolean update)	如果当前值==期望值，则将该值原子设置为给定的更新值。
2	public boolean get()	返回当前值。
3	public boolean getAndSet(boolean newValue)	将原子设置为给定值并返回上一个值。
4	public void lazySet(boolean newValue)	最终设定为给定值。
5	public void set(boolean newValue)	无条件地设置为给定的值。
6	public String toString()	返回当前值的String表示形式。
7	public boolean weakCompareAndSet(boolean expect, boolean update)


Thread 1 Waiting for Thread 2 to set Atomic variable to true. Current value is false
Thread 1 Waiting for Thread 2 to set Atomic variable to true. Current value is false
Thread 2, Atomic Variable: false
Thread 2 is setting the variable to true 
Thread 2, Atomic Variable: true
Thread 1 Waiting for Thread 2 to set Atomic variable to true. Current value is false
Done!

 * */

