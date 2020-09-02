package com.zsc.example.nobody.proxy;

/**
 * @program: nobody_demo
 * @description: 代理类
 * @author: zhangsc
 * @create: 2020-05-29 15:43
 **/
public class HelloProxy implements HelloInterface{
    private HelloInterface helloInterface = new Hello();
    @Override
    public void sayHello() {
        System.out.println("Before invoke sayHello" );
        helloInterface.sayHello();
        System.out.println("After invoke sayHello");
    }
}