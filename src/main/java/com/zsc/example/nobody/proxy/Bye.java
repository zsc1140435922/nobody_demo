package com.zsc.example.nobody.proxy;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-05-29 16:09
 **/
public class Bye implements ByeInterface {
    @Override
    public void sayBye() {
        System.out.println("Bye zhanghao!");
    }
}