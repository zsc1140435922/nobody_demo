package com.zsc.example.nobody.proxy.adaptive.one;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2021-06-21 10:14
 **/
//创建接口
public interface Port {
    // 远程SSH端口22
    public void SSH();

    // 网络端口80
    public void NET();
}