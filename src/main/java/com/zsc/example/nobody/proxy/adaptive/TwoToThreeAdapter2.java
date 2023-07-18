package com.zsc.example.nobody.proxy.adaptive;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2021-06-21 09:43
 **/
/**
 *  二项转三项的适配器  继承的方式  类
 */
public class TwoToThreeAdapter2 extends TwoPower implements ThreePower {


    @Override
    public void powerByThree() {
        System.out.println("借助继承适配器转化二项电");
        this.powerByTwo();
    }
}