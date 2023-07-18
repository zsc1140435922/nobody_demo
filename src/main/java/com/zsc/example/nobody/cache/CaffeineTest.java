package com.zsc.example.nobody.cache;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2022-02-23 15:19
 **/
public class CaffeineTest {
    /**
     * initialCapacity=[integer]: 初始的缓存空间大小
     * maximumSize=[long]: 缓存的最大条数
     * maximumWeight=[long]: 缓存的最大权重
     * expireAfterAccess=[duration]: 最后一次写入或访问后经过固定时间过期
     * expireAfterWrite=[duration]: 最后一次写入后经过固定时间过期
     * refreshAfterWrite=[duration]: 创建缓存或者最近一次更新缓存后经过固定的时间间隔，刷新缓存
     * weakKeys: 打开key的弱引用
     * weakValues：打开value的弱引用
     * softValues：打开value的软引用
     * recordStats：开发统计功能
     * 注意：
     * expireAfterWrite和expireAfterAccess同事存在时，以expireAfterWrite为准。
     * maximumSize和maximumWeight不可以同时使用
     * weakValues和softValues不可以同时使用
     */

    public static void main(String[] args) throws InterruptedException {
        Cache<Object, Object> cache = Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.SECONDS)
                .expireAfterAccess(30, TimeUnit.SECONDS)
                .maximumSize(100000)
                .build();

        cache.put("1","11");
        Thread.sleep(10000L);
        cache.put("2","22");


        while (true){
            System.out.println("get 1:" + cache.getIfPresent("1"));
            System.out.println("get 2:" + cache.getIfPresent("2"));
            Thread.sleep(1000L);
        }

//        Object o= cache.get(id, v-> userDao.getOne(id));//
    }
}


