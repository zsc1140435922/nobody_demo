package com.zsc.example.nobody.proxy.adaptive;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2021-06-21 09:41
 **/

/**
 * 笔记本电脑 这是使用组合模式的-适配器模式
 */
public class NoteBook {

    /**
     * 期望的三项供电接口
     */
    private ThreePower threePower;

    public NoteBook(ThreePower threePower) {
        this.threePower = threePower;
    }


    public static void main(String[] args) {
        // ============================ 继承方式的适配器使用 类适配器 =====================================
        ThreePower threePower1 = new TwoToThreeAdapter2();
        NoteBook noteBook1 = new NoteBook(threePower1);
        noteBook1.recharge();
        noteBook1.work();

        // ============================ 组合方式的适配器使用 对象适配器 =====================================
        // 现在只有二项供电
        TwoPower twoPower = new TwoPower();
        ThreePower threePower = new TwoToThreeAdapter(twoPower);
        NoteBook noteBook = new NoteBook(threePower);
        // 1. 充电
        noteBook.recharge();
        // 2. 工作
        noteBook.work();
    }

    public void work() {
        System.out.println("笔记本电脑开始工作!");
    }

    public void recharge() {
        // 使用三项充电
        threePower.powerByThree();
    }
}

/*
控制台显示:
        　　提供二项供电　　
        借助继承适配器转化二项电　　
        笔记本电脑开始工作!


        　　提供二项供电　　
        借助组合适配器转化二项电　　
        笔记本电脑开始工作!*/
