package com.zsc.example.nobody.exception;


/**
 * @program: prana
 * @description:
 * @author: zhangsc
 * @create: 2022-01-14 18:41
 **/
public class JavaSetDefaultExceptioneExp implements Runnable {
    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new JavaSetDefaultExceptioneExp());
        // call run() function
        thread.start();
    }
}