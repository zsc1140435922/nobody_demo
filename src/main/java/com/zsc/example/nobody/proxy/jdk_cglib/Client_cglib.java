package com.zsc.example.nobody.proxy.jdk_cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-06-01 11:28
 **/
public class Client_cglib {
    public static void main(String[] args) {
        // 1
        final PersonService actor = new PersonServiceImpl();
        /**
         * 涉及的类：Enhancer
         * 创建代理对象的方法：create()
         * 该方法的参数：
         *        Class:被代理对象的字节码
         *        Callback:如何代理，作用同方法一的InvocationHandler
         */
        PersonService proxyActor = (PersonServiceImpl) Enhancer.create(actor.getClass(), new MethodInterceptor() {
            /**
             * 执行被代理对象的方法都会经过该方法，作用同方法一的invoke()
             * Object proxy：代理对象的引用。不一定每次都会有
             * Method method：当前执行的方法
             * Object[] args：当前执行方法所需的参数
             * MethodProxy methodProxy:当前执行方法的代理对象，一般不用
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                Object ret = null;
                //1.取出执行方法中的参数
//                Float money = (Float) args[0];
                //2.判断当前执行的什么方法
                if ("savePerson".equals(method.getName())) {
                    System.out.println("savePerson_before");
                    ret = method.invoke(actor);
                    System.out.println("savePerson_after");

                }
                if ("advancedAct".equals(method.getName())) {
                    ret = method.invoke(actor);

                }
                return ret;
            }
        });

        proxyActor.savePerson();


        // 2
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CglibPersonService.class);
        enhancer.setCallback(new CglibProxyIntercepter());
        CglibPersonService proxy= (CglibPersonService)  enhancer.create();
        proxy.savePerson();




    }
}
