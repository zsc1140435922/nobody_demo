package com.zsc.example.nobody.diff;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.apache.hadoop.security.SaslOutputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2022-11-07 19:56
 * @Copyright: HOSE合思｜易快报
 **/
public class Test01 {
    public static void main(String[] args) {
        Person s = new Person(1,"s");
        Person s2 = new Person(2,"2s");
        Person s3 = new Person(3,"3s");
        List list = new ArrayList<>();
        list.add(s);
        list.add(s2);
        list.add(s3);
        Map<String, List<Person>> map = Maps.newHashMap();
        map.put("zsc", list);
        System.out.println(JSON.toJSONString(map));
        List list2 = map.get("zsc");
        list2.remove(s2);
        System.out.println(JSON.toJSONString(map));

    }
}
