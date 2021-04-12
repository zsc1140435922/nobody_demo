package com.zsc.example.nobody;

import com.google.gson.Gson;
import com.zsc.example.nobody.interceptor.SinaIpVo;
import com.zsc.example.nobody.spi.SpiDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.ServiceLoader;

/**
 * @author
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("启动成功");

    }

}
