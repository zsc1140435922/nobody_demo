package com.zsc.example.nobody.proxy.adaptive.one;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2021-06-21 10:14
 **/
//伪实现接口 定义抽象类实现端口接口，但是什么事情都不做
public abstract class Wrapper implements Port{
    @Override
    public void SSH(){};

    @Override
    public void NET(){}
}