package com.zsc.example.nobody.guava;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * @program: nobody_demo
 * @description: GuavaCache
 * @author: zhangsc
 * @create: 2019-12-12 10:28
 **/
public class GuavaCache {
    static Cache<String,String> userNameCache =
            CacheBuilder.newBuilder().maximumSize(3).
                    expireAfterAccess(5000, TimeUnit.MILLISECONDS).build();

    //CacheBuilder类构建一个缓存对象
    static Cache<String,String> cache = CacheBuilder.newBuilder().build();


    public static void main(String[] args) throws Exception {


        String s = userNameCache.get("zsc", new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("---张世闯");
                return "张世闯";
            }
        });
        System.out.println(s);
        String a = userNameCache.get("zsc", new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("---ok");
                return "ok";
            }
        });
        userNameCache.get("zsc1", new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("---ok");
                return "ok";
            }
        });


        System.out.println(a);
        System.out.println("^" +userNameCache.getIfPresent("11"));
        ConcurrentMap map = userNameCache.asMap();
        System.out.println(JSON.toJSON(map));

        cache.put("word","Hello Guava Cache");
        System.out.println(cache.getIfPresent("word"));









    }
}
