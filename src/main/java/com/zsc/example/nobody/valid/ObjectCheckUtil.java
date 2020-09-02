package com.zsc.example.nobody.valid;

import com.zsc.example.nobody.stream.Student;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-08-31 09:42
 **/
public class ObjectCheckUtil {
    private static final Logger log = LoggerFactory.getLogger(ObjectCheckUtil.class);
    /**
     * 传入对象,检查对象是否为null或者所有的属性为空/null,不校验boolean类型为false的情况
     * @return
     */
    public static boolean checkNullAndEmpty(Object obj){
        if(null == obj){
            return true;
        }else if (obj instanceof CharSequence){
            return ((CharSequence) obj).length() == 0;
        }
//        else if (obj instanceof Collection){
//            return ((Collection) obj).isEmpty();
//        }
        else if (obj instanceof Map){
            return ((Map) obj).isEmpty();
        }
        else if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        Class<?> clzz = obj.getClass();
        Field[] allFields = getAllFields(clzz);
        for (Field field : allFields) {
            if(Modifier.isFinal(field.getModifiers()) || Modifier.isStatic(field.getModifiers()) || Modifier.isAbstract(field.getModifiers())){
                continue;
            }
            if(!field.isAccessible()){
                field.setAccessible(true);
            }
            try {
                Object o = field.get(obj);
                //处理String,排除空字符串
                if(o instanceof String){
                    if(StringUtils.isNotEmpty((String) o)){
                        return false;
                    }
                }
                if(o instanceof Boolean){
                    //boolean类型且默认为true
                    if((Boolean) o){
                        return false;
                    }
                    continue;
                }
                if(o != null){
                    return false;
                }
                if(o instanceof Collection){
                    if(!((Collection) obj).isEmpty()){
                        return false;
                    }
                }
                if(o instanceof Map){
                    if(!((Map) obj).isEmpty()){
                        return false;
                    }
                }
                if(o != null && o.getClass().isArray() ){
                    if(Array.getLength(obj) > 0){
                        return false;
                    }
                }
            } catch (IllegalAccessException e) {
                log.error("get obj by reflect error{}:",field.getName(),e);
            }
        }
        return true;
    }


    public static Field[] getAllFields(Class c){
        List<Field> fieldList = new ArrayList<>();
        while (c!= null){
            fieldList.addAll(new ArrayList<>(Arrays.asList(c.getDeclaredFields())));
            c= c.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }

    public static void main(String[] args) {
        List<Student> list = new ArrayList<>(2);
        Student s = new Student("zsc",1,1,1);
        list.add(s);
        boolean b = checkNullAndEmpty(list);
        System.out.println();
    }

}
