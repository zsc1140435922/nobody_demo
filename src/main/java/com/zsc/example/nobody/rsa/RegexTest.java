package com.zsc.example.nobody.rsa;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: nobody_demo
 * @description: 测试字符串是否是正则表达式
 * @author: zhangsc
 * @create: 2022-04-01 11:36
 **/
public class RegexTest {
    public static void main(String[] args) {
        String passwd = "rqbkjhrjqh1111@!45";
//        String regex = "^[A-Za-z]|[0-9]|[!@#$%^&*]{6,18}$";
//        if(!passwd .matches(regex)){
//            System.out.println("不是");
//        }


//        String regex = "^[A-Za-z]|[0-9]|[!@#$%^&*]{6,18}$";
//        Pattern pattern = Pattern.compile(regex);    // 编译正则表达式
//        Matcher matcher = pattern.matcher(passwd);
//        if (!matcher.matches()) {
//            System.out.println("不是");
//        }


        String str = "^[A-Za-z]|[0-9]|[!@#$%^&*]{6,18}$";
        String regex = "http://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*";
        if (!regex.matches(str)) {
            System.out.println("不是");
        }

    }
}
