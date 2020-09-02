package com.zsc.example.nobody.proxy.jdk_cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-06-01 14:09
 **/
public class CglibProxyIntercepter implements MethodInterceptor {

    @Override
    public Object intercept(Object sub, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("执行前...");
        Object object = methodProxy.invokeSuper(sub, objects);
        System.out.println("执行后...");
        return object;
    }
}
