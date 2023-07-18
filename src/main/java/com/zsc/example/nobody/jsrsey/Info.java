package com.zsc.example.nobody.jsrsey;

import java.lang.annotation.*;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2021-10-22 15:41
 **/
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Info {
    String value();
}