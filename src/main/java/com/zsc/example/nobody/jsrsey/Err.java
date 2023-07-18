package com.zsc.example.nobody.jsrsey;

import java.lang.annotation.*;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2021-10-22 15:40
 **/
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Err {
    String value();
}