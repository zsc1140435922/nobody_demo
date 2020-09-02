package com.zsc.example.nobody.gson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;

import java.util.Map;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-02-08 15:47
 **/
public class GsonTest {
    public static void main(String[] args) {
        User u = new User();
//        u.setAgeInfo("12345");
        u.setNameInfo("zsc");

        //1.Fastjson
        SerializeConfig config = new SerializeConfig();
        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        String json = JSON.toJSONString(u, config);
        Map pMap = JSONObject.parseObject(json);
        System.out.println(json);

        //2.Gson
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
//        Gson gson = gsonBuilder.create();
//        String json = gson.toJson(u);
//        System.out.println(json);

        //3.Jackson
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.setPropertyNamingStrategy(com.fasterxml.jackson.databind.PropertyNamingStrategy.SNAKE_CASE);
//        String json = null;
//        try {
//            json = mapper.writeValueAsString(u);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        System.out.println(json);
//        Map pMap = JSONObject.parseObject(json);
//        Assert.checkNull(pMap);
    }
}
