package com.zsc.example.nobody.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2021-01-21 16:27
 **/
@Aspect
@Component
@Order(2)
public class LogFilterAspect2 {
    @Pointcut(value = "@annotation(com.zsc.example.nobody.aop.LogFilter2)")
    public void pointCut(){
    }

    /**
     * 环绕通知，可以说是使用最频繁的方式，会将整个方法包围起来
     * @param joinPoint
     * @return
     */
    @Around(value = "pointCut()")
    public Object round(ProceedingJoinPoint joinPoint){
        System.out.println("2、Round begin");
        Object obj = null;
        try {
            obj = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("2、Round end");
        return obj;
    }

    /**
     * 前置方法，在目标方法执行前触发
     */
    @Before(value = "pointCut()")
    public void before(){
        System.out.println("2、Before");
    }


    /**
     * 后置方法，与@Before相反，在目标方法执行完毕后执行
     */
    @After(value = "pointCut()")
    public void after(){
        System.out.println("2、After");
    }

    /**
     * 后置通知，在将返回值返回时执行
     * @param result
     */
    @AfterReturning(value = "pointCut()", returning = "result")
    public void afterReturning(Object result){
        System.out.println("2、AfterReturning");
    }
}
