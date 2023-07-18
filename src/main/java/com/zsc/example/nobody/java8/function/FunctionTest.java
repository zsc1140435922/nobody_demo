package com.zsc.example.nobody.java8.function;

import java.util.function.Function;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2021-12-28 14:24
 **/
public class FunctionTest {
    public static void main(String[] args) {
        Function<String, String> function = a -> a + " Jack!";
        System.out.println(function.apply("Hello")); // Hello Jack!

        Function<Integer, Integer> fun = x -> x * 2;
        System.out.println(fun.apply(3));//6
        Function<Integer, Integer> fun1 = x -> x + 2;
        System.out.println(fun1.apply(3));//5
        //所以Function中没有具体的操作，具体的操作需要我们去为它指定，因此apply具体返回的结果取决于传入的lambda表达式。
    }
}
