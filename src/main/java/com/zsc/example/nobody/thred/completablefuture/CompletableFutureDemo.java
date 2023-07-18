package com.zsc.example.nobody.thred.completablefuture;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2022-04-15 15:56
 **/
public class CompletableFutureDemo {
    public static void exceptionally() throws Exception {
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> "执行：" + 10 / 0)
                .thenApply(s -> {
                    System.out.println("执行了");
                    s += "a";
                    return s;
                })
                //.exceptionally(e -> "100")
                .exceptionally(e -> {
                    System.out.println(e.getMessage());
                    return "100";
                });
        System.out.println(futureA.get());
    }

    static void whenComplete() throws Exception {

        CompletableFuture<Integer> futureA = CompletableFuture.supplyAsync(() -> 10 / 0)
                .thenApply(s -> {
                    System.out.println("执行了");
                    return s + 1;
                })
                .whenComplete((r, e) -> {
                    if (r != null && e == null) {
                        System.out.println("正常执行");
                    }
                    if (e != null) {
                        System.out.println("执行异常：" + e.getMessage());
                    }
                });
        System.out.println(futureA.get());
    }

    public static void main(String[] args) throws Exception {
//        exceptionally();
        System.out.printf(demo().toString());
        CompletableFuture futureq = demo();
        //thenApply 改变返回结果 是返回的是非CompletableFuture类型：
        //它的功能相当于将CompletableFuture<T>转换成CompletableFuture<U>
        CompletableFuture future = demo().thenApply(msgid -> "zsc");
        System.out.printf(future.toString());

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            return 100;

        });
//thenCompose 来连接两个CompletableFuture，返回值是新的CompletableFuture：
        CompletableFuture<String> f = future2.thenCompose(i -> {

            return CompletableFuture.supplyAsync(() -> {

                return (i * 10) + "";

            });

        });
        System.out.printf("11");
    }

    static CompletableFuture<List<String>> demo() {
        List<String> arr = new ArrayList<>();
        CompletableFuture<List<String>> result = CompletableFuture.completedFuture(arr);
        arr.add("hesi");
        arr.add("ekb");
        return result;
    }


}
