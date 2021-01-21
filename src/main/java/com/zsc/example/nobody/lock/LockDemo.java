package com.zsc.example.nobody.lock;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import sun.nio.ch.ThreadPool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-05-22 17:02
 **/
public class LockDemo {
    public static void main(String[] args) throws InterruptedException {

        final Lock lock = new ReentrantLock(true);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                lock.lock();
//                System.out.println(Thread.currentThread().getName() + " 获得锁");
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                lock.unlock();
//            }
//        }, "thread0").start();

//        for (int i = 1; i <= 10; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    lock.lock();
//                    System.out.println(Thread.currentThread().getName() + " 获得锁");
//                    System.out.println("two" + Thread.currentThread().getName() + " 获得锁");
//                    lock.unlock();
//                }
//            }, "thread" + i).start();
////            try {
////                Thread.sleep(1);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
//        }
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记

//        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
//                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
//        executorService.scheduleAtFixedRate(() -> {
//            // 预定在初始的延迟结束后，周期性地运行给定的任务，周期长度是period
//
//            try {
//                Thread.sleep(5000L);
//                System.out.println("ok" + Thread.currentThread().getName());
//                Date date = new Date();// 获取当前时间
//                System.out.println("现在时间：" + sdf.format(date));
//                System.out.println();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            //执行的任务
//        },0,2, TimeUnit.SECONDS);


        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
        service.scheduleWithFixedDelay(() -> {
// 预定在初始的延迟结束后周期性的给定任务，在一次调用完成和下一次调用开始之间有长度为delay的延迟
            try {
                Thread.sleep(3000L);
                System.out.println("ok" + Thread.currentThread().getName());
                Date date = new Date();// 获取当前时间
                System.out.println("现在时间：" + sdf.format(date));
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //执行的任务
        },0,2, TimeUnit.SECONDS);

        Thread.sleep(100000L);





    }
}