//package com.zsc.example.nobody.redis;
//
//import redis.clients.jedis.Jedis;
//
///**
// * @program: nobody_demo
// * @description:
// * @author: zhangsc
// * @create: 2020-05-09 12:41
// **/
//public class redisMain {
//    //开启redis 镜像
//    public static void main(String[] args) {
//        //连接本地的 Redis 服务
//        Jedis jedis = new Jedis("127.0.0.1",6379);
//        //查看服务是否运行
//        System.out.println("服务正在运行: "+jedis.ping());
//        System.out.println(jedis.get("zsc"));
//    }
//}
