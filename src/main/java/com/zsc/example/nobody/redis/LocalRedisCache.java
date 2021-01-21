//package com.zsc.example.nobody.redis;
//
//import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;
//
///**
// * @program: nobody_demo
// * @description:
// * @author: zhangsc
// * @create: 2020-09-21 16:52
// **/
//public class LocalRedisCache {
//    private static  LocalRedisCache cache;
//    private LocalRedisCache() {
//
//    }
//    public static LocalRedisCache getInstance() {
//        if (cache == null){
//
//        }
//        return cache;
//    }
//
//    public static void main(String[] args) {
//        FlinkJedisPoolConfig conf = new FlinkJedisPoolConfig.Builder().setHost("127.0.0.1").build();
//        System.out.println(conf.getPort());
//    }
//
//}
