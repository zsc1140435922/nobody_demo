package com.zsc.example.nobody.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-07-12 17:17
 **/
public class Main {
    public static void main(String[] args) {
        ServiceLoader<SpiDemo> serviceLoader = ServiceLoader.load(SpiDemo.class);
        for (SpiDemo o : serviceLoader) {
            o.say();
        }
    }
}
