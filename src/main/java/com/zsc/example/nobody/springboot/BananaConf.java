package com.zsc.example.nobody.springboot;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Configuration;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2021-04-12 15:53
// **/
@Configuration
//BananaConf 在AppleConf之前加载
@AutoConfigureBefore(AppleConf.class)
public class BananaConf {
    BananaConf(){
        System.out.println("BananaConf");
    }

}
