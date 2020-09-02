package com.zsc.example.nobody.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-05-09 12:37
 **/
//@Configuration
//@EnableCaching
//public class RedisConfig extends CachingConfigurerSupport {
//    @Value("${spring.redis.host}")
//    private String host;
//    @Value("${spring.redis.port}")
//    private int port;
//    @Value("${spring.redis.timeout}")
//    private int timeout;
//    @Value("${spring.redis.password}")
//    private String password;
//    @Value("${spring.redis.pool.max-active}")
//    private int maxActive;
//    @Value("${spring.redis.pool.max-wait}")
//    private int maxWait;
//    @Value("${spring.redis.pool.max-idle}")
//    private int maxIdle;
//    @Value("${spring.redis.pool.min-idle}")
//    private int minIdle;
//}
