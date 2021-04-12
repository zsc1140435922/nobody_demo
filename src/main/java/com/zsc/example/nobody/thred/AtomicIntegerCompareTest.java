package com.zsc.example.nobody.thred;

import java.util.concurrent.atomic.AtomicInteger;  
public class AtomicIntegerCompareTest {  
    private int value;  

    public AtomicIntegerCompareTest(int value){  
        this.value = value;  
    }  

    public synchronized int increase(){  
        return value++;  
    }  

    public static void main(String args[]){  
        long start = System.currentTimeMillis();  

        AtomicIntegerCompareTest test = new AtomicIntegerCompareTest(0);  
        for( int i=0;i< 1000000;i++){  
            test.increase();  
        }  
        long end = System.currentTimeMillis();  
        System.out.println("time elapse:"+(end -start));  

        long start1 = System.currentTimeMillis();  

        AtomicInteger atomic = new AtomicInteger(0);  

        for( int i=0;i< 1000000;i++){  
            atomic.incrementAndGet();  
        }  
        long end1 = System.currentTimeMillis();  
        System.out.println("time elapse:"+(end1 -start1) );  


    }  
}

/**
 * 由此不难看出，通过JNI本地的CAS性能远超synchronized关键字
优点总结:
最大的好处就是可以避免多线程的优先级倒置和死锁情况的发生，提升在高并发处理下的性能。

使用AtomicInteger是非常的安全的。而且因为AtomicInteger由硬件提供原子操作指令实现的。在非激烈竞争的情况下，开销更小，速度更快。
 * 
 * public final int get() //获取当前的值
public final int getAndSet(int newValue)//获取当前的值，并设置新的值
public final int getAndIncrement()//获取当前的值，并自增
public final int getAndDecrement() //获取当前的值，并自减
public final int getAndAdd(int delta) //获取当前的值，并加上预期的值
 * 
 * **/
