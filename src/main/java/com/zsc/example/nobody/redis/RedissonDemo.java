//package com.zsc.example.nobody.redis;
//
//import org.redisson.Redisson;
//import org.redisson.api.RBucket;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @program: nobody_demo
// * @description:
// * @author: zhangsc
// * @create: 2020-06-02 09:36
// **/
//public class RedissonDemo {
//    /**
//     *     void set(V var1);  //设置桶存储的对象
//     *     void set(V var1, long var2, TimeUnit var4);  //设置桶存储的对象，设置操作的超时时间var2
//     *
//     *     boolean trySet(V var1);  //尝试设置桶的新值
//     *     boolean trySet(V var1, long var2, TimeUnit var4);  //尝试设置桶的新值，设置超时时间var2
//     *
//     *     boolean compareAndSet(V var1, V var2); //原子替换桶的新值为var2
//     *
//     *     long size();       //桶存储对象的大小
//     *
//     *     V get();           //返回桶存储的对象
//     *     V getAndDelete();  //返回并删除桶存储的对象
//     *
//     *     V getAndSet(V var1);//返回桶的旧值，设置新值
//     *     V getAndSet(V var1, long var2, TimeUnit var4);  //返回桶的旧值，设置新值，设置操作的超时时间var2
//     *
//     * @param args
//     */
//    public static void main(String[] args) {
////
////        Config config = new Config();
////
////        config.useSingleServer().setAddress("127.0.0.1:6379");
////
////        RedissonClient redisson = Redisson.create(config);
//        RedissonClient redisson = Redisson.create();
//        RBucket<Integer> bucket = redisson.getBucket("zsc");
//        bucket.set(3, 5, TimeUnit.SECONDS);
//        Integer obj = bucket.get();
//
//        bucket.trySet(3);
//            Integer obj1 = bucket.get();
//        boolean set = bucket.compareAndSet(1, 2);
//        Integer obj2 = bucket.get();
//        Integer obj3 = bucket.getAndSet(6);
//        System.out.println("ok");
//    }
//}
