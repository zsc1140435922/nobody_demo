package com.zsc.example.nobody.thred.yace;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class ConcurrentTestUtil {

    /**
     * 多线程并发执行某项任务
     *
     * @param concurrentThreads    并发线程数，可以用来模拟并发访问用户数
     * @param times                总共执行多少次
     * @param task                 任务
     * @param requestHandler        结果处理器
     * @param executeTimeoutMillis 执行任务总超时
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static <T> void concurrentTest(long concurrentThreads, int times, final Callable<T> task,
                                          RequestHandler<T> requestHandler, long executeTimeoutMillis)
            throws InterruptedException, ExecutionException {

        ExecutorService executor = Executors.newFixedThreadPool((int) concurrentThreads);
        List<Future<T>> results = new ArrayList<Future<T>>(times);

        long startTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            results.add(executor.submit(task));
        }
        executor.shutdown();

        //awaitTermination会一直等待，直到线程池状态为TERMINATED或者，等待的时间到达了指定的时间。
        boolean executeCompleteWithinTimeout = executor.awaitTermination(executeTimeoutMillis,TimeUnit.MILLISECONDS);
        if (!executeCompleteWithinTimeout) {
            System.out.println("Execute tasks out of timeout [" + executeTimeoutMillis + "ms]");

            /*
             * 取消所有任务
             */
            for (Future<T> r : results) {
                r.cancel(true);
            }
        } else {
            long totalCostTimeMillis = System.currentTimeMillis() - startTimeMillis;

            // 线程池此时肯定已关闭，处理任务结果
            for (Future<T> r : results) {
                if (requestHandler != null) {
                    requestHandler.handle(r.get());
                }
            }

            System.out.println("线程数: " + concurrentThreads + ", 总执行次数: "   + times);
            System.out.println("总耗时（ms）: " + totalCostTimeMillis  + "ms, 执行每次耗时 avg(ms): " + ((double) totalCostTimeMillis / times));
            System.out.println("每秒执行 tps: " + (double) (times * 1000) / totalCostTimeMillis);
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ConcurrentTestUtil.concurrentTest(2, 100000,
                new Callable<String>() {
                    @Override
                    public String call() throws Exception {
//                        try {
//                            Thread.sleep(10);
//                        } catch (InterruptedException e) {
//                            Thread.currentThread().interrupt();
//                        }
                        return "ok";
                    }
                },
                new RequestHandler<String>() {
                    @Override
                    public void handle(String result) {
                        System.out.println("result: " + result);
                    }
                }, 5000);
    }
}