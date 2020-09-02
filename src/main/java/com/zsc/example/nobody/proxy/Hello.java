package com.zsc.example.nobody.proxy;

/**
 * @program: nobody_demo
 * @description: 被代理类
 * @author: zhangsc
 * @create: 2020-05-29 15:43
 **/
public class Hello implements HelloInterface{
    @Override
    public void sayHello() {
        System.out.println("Hello zhanghao!");
    }
}
