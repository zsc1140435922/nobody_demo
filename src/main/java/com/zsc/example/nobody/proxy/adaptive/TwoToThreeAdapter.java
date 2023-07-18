package com.zsc.example.nobody.proxy.adaptive;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2021-06-21 09:43
 **/
/**
 * 二项转三项的适配器  组合的方式  对象适配器
 */
public class TwoToThreeAdapter implements ThreePower{

    /**
     * 使用委托来完成适配
     */
    private TwoPower twoPower;

    public TwoToThreeAdapter(TwoPower twoPower) {
        this.twoPower = twoPower;
    }


    @Override
    public void powerByThree() {
        System.out.println("借助组合适配器转化二项电");
        twoPower.powerByTwo();
    }
}