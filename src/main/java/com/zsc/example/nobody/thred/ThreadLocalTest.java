package com.zsc.example.nobody.thred;

import java.util.UUID;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-04-03 21:07
 **/
public class ThreadLocalTest {
    static  ThreadLocal<String> local = new ThreadLocal<String>();
    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        local.set(uuid);


        String uuid2 = UUID.randomUUID().toString();
        System.out.println(uuid2);
        local.set(uuid2);
        System.out.println(local.get());
        System.out.println(local.get());

        Thread t = Thread.currentThread();
        Thread t2 = Thread.currentThread();
        System.out.println(t.getName());
        System.out.println(t2.getName());
    }
}
