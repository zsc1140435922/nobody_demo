package com.zsc.example.nobody.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: nobody_demo
 * @description: 动态代理
 * @author: zhangsc
 * @create: 2020-05-29 15:49
 **/
public class ProxyHandler implements InvocationHandler {
    private Object object;
    public ProxyHandler(Object object){
        this.object = object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before invoke "  + method.getName());
        method.invoke(object, args);
        System.out.println("After invoke " + method.getName());
        return null;
    }
}