package com.zsc.example.nobody.proxy.jdk_cglib;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: nobody_demo
 * @description: 在动态代理在生成代理对象的时候需要一个拦截器 InvocationHandler
 * @author: zhangsc
 * @create: 2020-05-29 16:30
 **/
public class PersonServiceInterceptor implements InvocationHandler {
    //目标类
    private Object target;
    //增强类
    private MyTransaction myTransaction;
    //构造函数注入目标类和增强类
    public PersonServiceInterceptor(Object target,MyTransaction myTransaction){
        this.target = target;
        this.myTransaction = myTransaction;
    }

    //代理类的每一个方法被调用的时候都会调用下边的这个invoke方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        this.myTransaction.beginTransaction();
        Object returnValue = method.invoke(this.target, args);
        this.myTransaction.commit();
        return returnValue;
    }


}
