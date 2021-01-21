package com.zsc.example.nobody.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2021-01-21 16:31
 **/
@Target(ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface LogFilter1 {
}