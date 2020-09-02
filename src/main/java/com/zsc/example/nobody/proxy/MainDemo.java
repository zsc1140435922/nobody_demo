package com.zsc.example.nobody.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-05-29 15:48
 **/
public class MainDemo {
    public static void main(String[] args) {
        //静态代理
//        HelloProxy helloProxy = new HelloProxy();
//        helloProxy.sayHello();


        //动态代理
        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        HelloInterface hello = new Hello();
        InvocationHandler handler = new ProxyHandler(hello);
        HelloInterface proxyHello = (HelloInterface) Proxy.newProxyInstance(hello.getClass().getClassLoader(), hello.getClass().getInterfaces(), handler);
        proxyHello.sayHello();



        ByeInterface bye = new Bye();
        InvocationHandler handler1 = new ProxyHandler(bye);
        ByeInterface proxyBye = (ByeInterface) Proxy.newProxyInstance(bye.getClass().getClassLoader(), bye.getClass().getInterfaces(), handler1);
        proxyBye.sayBye();
    }
}
