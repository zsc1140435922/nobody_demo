package com.zsc.example.nobody.map;


import com.google.common.collect.Maps;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-06-02 17:16
 **/
public class MapDemo {
    public static void main(String[] args) {
        HashMap map = Maps.newHashMap();
        map.put("0","0");
//        map.put("1","1");
//        map.put("2","2");
//        map.put("3","3");
//        map.put("4","4");
//        map.put("5","5");
//        map.put("6","6");
//        map.put("7","7");
//        map.put("8","8");
//        map.put("9","9");
//        map.put("10","10");
//        map.put("11","11");
//        map.put("12","12");
//        map.put("13","13");
//        map.put("14","14");
//        map.put("15","15");
//        map.put("16","16");
//        map.put("17","17");
        System.out.println(map.size());
        ObjectMapper mapper = new ObjectMapper();
        try {

            //
            String string = mapper.writeValueAsString(map);

            mapper.readValue(string, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
