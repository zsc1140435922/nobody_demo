package com.zsc.example.nobody.exception;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-08-11 10:43
 **/
public class TestException2 extends APIException{
    public static void main(String[] args) {
        try {
            int a=0;
            int b = 0;
            System.out.println(a/b);
        } catch (Exception e){
e.printStackTrace();
        }

    }
}
