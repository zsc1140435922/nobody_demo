package com.zsc.example.nobody.proxy.adaptive.one;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2021-06-21 10:18
 **/
public class Start {
    private static Port chatPort = new Chat();

    public static void main(String[] args) {
// 聊天服务
        chatPort.SSH();
        chatPort.NET();
    }
}
