package com.zsc.example.nobody.proxy.jdk_cglib;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-05-29 16:29
 **/
public class MyTransaction {
    public void beginTransaction(){
        System.out.println("开启事务 ");
    }
    public void commit(){
        System.out.println("提交事务");
    }
}
