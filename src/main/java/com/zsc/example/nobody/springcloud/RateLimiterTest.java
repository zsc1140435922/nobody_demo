package com.zsc.example.nobody.springcloud;

import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

/**
 * @program: nobody_demo
 * @description: 简单的令牌桶限流
 * @author: zhangsc
 * @create: 2020-01-14 16:12
 **/
public class RateLimiterTest {
    /**
     * 桶的大小
     */
    private Integer limit;

    /**
     * 桶当前的token
     */
    private static Integer tokens = 0;

    /**
     * 构造参数
     */
    public RateLimiterTest(Integer limit, Integer speed){
        //初始化桶的大小，且桶一开始是满的
        this.limit = limit;
        tokens = this.limit;

        //任务线程：每秒新增speed个令牌
        new Thread(() ->{
            while (true){
                try {
                    Thread.sleep(1000L);

                    int newTokens = tokens + speed;
                    if(newTokens > limit){
                        tokens = limit;
                        System.out.println("令牌桶满了！！！");
                    }else{
                        tokens = newTokens;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 根据令牌数判断是否允许执行，需要加锁
     */
    public synchronized boolean execute() {
        if (tokens > 0) {
            tokens = tokens - 1;
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        //1.令牌桶限流：峰值每秒可以处理10个请求，正常每秒可以处理2个请求
//        RateLimiterTest rateLimiter = new RateLimiterTest(10, 2);

        //模拟请求
//        while (true) {
//            //在控制台输入一个值按回车，相对于发起一次请求
//            Scanner scanner = new Scanner(System.in);
//            scanner.next();
//
//            //令牌桶返回true或者false
//            if (rateLimiter.execute()) {
//                System.out.println("允许访问");
//            } else {
//                System.err.println("禁止访问");
//            }
//        }

        //2.Google开源工具包Guava提供了限流工具类RateLimiter，该类基于令牌桶算法实现流量限制，使用十分方便，而且十分高效。
        RateLimiterTest.testAcquire();


    }

    /**
     * 首先通过RateLimiter.create(1);创建一个限流器，参数代表每秒生成的令牌数，通过limiter.acquire(i);来以阻塞的方式获取令牌，
     * 当然也可以通过tryAcquire(int permits, long timeout, TimeUnit unit)来设置等待超时时间的方式获取令牌，
     * 如果超timeout为0，则代表非阻塞，获取不到立即返回
     *
     */
    public static void testAcquire() {
        RateLimiter limiter = RateLimiter.create(1);
        for(int i = 1; i < 100; i ++ ) {
            //获取i个令牌
//            double waitTime = limiter.acquire(i);
//            System.out.println("cutTime=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS").format(Calendar.getInstance().getTime()) + " acq:" + i + " waitTime:" + waitTime);
            //
            try {
                Thread.sleep(10L);
                boolean boo = limiter.tryAcquire();
                System.out.println("cutTime=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS").format(Calendar.getInstance().getTime()) + " acq:" + i +",boo:"+boo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
