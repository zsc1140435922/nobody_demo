package com.zsc.example.nobody.spi;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-07-12 17:14
 **/
public class SpiDemoImpl1 implements SpiDemo {
    @Override
    public void say() {
        System.out.println("SpiDemoImpl1");
    }
}