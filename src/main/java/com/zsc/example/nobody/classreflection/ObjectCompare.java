package com.zsc.example.nobody.classreflection;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-03-05 15:08
 **/
public class ObjectCompare {
    public static void main(String[] args) throws Exception {
        User u = new User();
        u.setAge(10);
        u.setName("zsc");
        u.setSex("1");
        UserTwo t = new UserTwo();
        t.setAge(10);
        t.setName("zy");

        System.out.println(JSON.toJSONString(ObjectCompare.compareTwo(u,t)));


    }
    public static <T> Map<String, String> compareTwo(T obj1, T Obj2)
            throws Exception {

        Map<String, String> result = new HashMap<String, String>();
        Map<String, Object> mapObj2 = new HashMap<String, Object>();

        Field[] fs2 = Obj2.getClass().getDeclaredFields();
        for (Field f : fs2) {
            f.setAccessible(true);
            Object v = f.get(Obj2);
            mapObj2.put(f.getName(),v);
        }
        Field[] fs1 = obj1.getClass().getDeclaredFields();
        for (Field f : fs1) {
            f.setAccessible(true);
            Object v = f.get(obj1);
            if (mapObj2.get(f.getName()) != null){
                result.put(f.getName(), String.valueOf(equals(v, mapObj2.get(f.getName()))));
            }
        }
        return result;
    }
    public static boolean equals(Object obj1, Object obj2) {

        if (obj1 == obj2) {
            return true;
        }
        if (obj1 == null || obj2 == null) {
            return false;
        }
        return obj1.equals(obj2);
    }
}
