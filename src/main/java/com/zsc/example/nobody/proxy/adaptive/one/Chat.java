package com.zsc.example.nobody.proxy.adaptive.one;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2021-06-21 10:15
 **/
//覆写伪接口实现一种功能
/**
 * 提供聊天服务
 * 需要网络和文件传输功能
 */
public class Chat extends Wrapper {
    @Override
    public void NET() {
        System.out.println("Hello world!");
    }
    @Override
    public void SSH() {
        System.out.println("File upload succeddful!");
    }

}
