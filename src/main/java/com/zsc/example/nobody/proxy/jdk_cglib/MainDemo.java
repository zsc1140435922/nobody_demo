package com.zsc.example.nobody.proxy.jdk_cglib;

import java.lang.reflect.Proxy;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-05-29 15:48
 **/
public class MainDemo {
    /**
     * jdk
     * 动态代理具体步骤：
     *
     * 1.通过实现 InvocationHandler 接口创建自己的调用处理器；
     * 2.通过为 Proxy 类指定 ClassLoader 对象和一组 interface 来创建动态代理类；
     * 3.通过反射机制获得动态代理类的构造函数，其唯一参数类型是调用处理器接口类型；
     * 4.通过构造函数创建动态代理类实例，构造时调用处理器对象作为参数被传入。
     *
     * @param args
     */
    public static void main(String[] args) {
        //1
//        Object target = new PersonServiceImpl();
//        MyTransaction myTransaction = new MyTransaction();
//        PersonServiceInterceptor interceptor = new PersonServiceInterceptor(target, myTransaction);
//        PersonService personService = (PersonService)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),interceptor);
//        String returnValue = (String)personService.savePerson();
//        System.out.println(returnValue);


        //2
        PersonService personService1 = (PersonService) Proxy.newProxyInstance(PersonService.class.getClassLoader(),
                new Class[]{PersonService.class},new PersonServiceInterceptor(new PersonServiceImpl(), new MyTransaction()));

        personService1.savePerson();

    }
}
