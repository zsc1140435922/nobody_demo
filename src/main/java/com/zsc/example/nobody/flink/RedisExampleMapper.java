//package com.zsc.example.nobody.flink;
//
//import org.apache.flink.api.java.tuple.Tuple2;
//import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
//import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
//import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;
//
///**
// * @program: nobody_demo
// * @description:
// * @author: zhangsc
// * @create: 2020-09-22 09:54
// **/
//public class RedisExampleMapper implements RedisMapper<Tuple2<String, String>> {
//
//    @Override
//    public RedisCommandDescription getCommandDescription() {
//        return new RedisCommandDescription(RedisCommand.HSET, "HASH_NAME");
//    }
//
//    @Override
//    public String getKeyFromData(Tuple2<String, String> data) {
//        return data.f0;
//    }
//
//    @Override
//    public String getValueFromData(Tuple2<String, String> data) {
//        return data.f1;
//    }
//}