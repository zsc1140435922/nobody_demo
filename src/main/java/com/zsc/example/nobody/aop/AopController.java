package com.zsc.example.nobody.aop;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2021-01-21 16:32
 **/
@RestController
public class AopController {

    @RequestMapping("/aop")
    public String aop(){
        System.out.println("这是执行方法");
        return "success";
    }
}