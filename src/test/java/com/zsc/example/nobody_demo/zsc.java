package com.zsc.example.nobody_demo;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-04-21 22:30
 **/
public class zsc {

    public static void main(String[] args)
    {
        AbstractGraph tm=new ConcreteClass();
        tm.draw();
        try {
            AbstractGraph t = ConcreteClass.class.newInstance();
            t.draw();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}

abstract class AbstractGraph
{
    public void draw()
    {
        SpecificMethod();
        circle();
        abstractMethod2();
    }
    public void SpecificMethod() //具体方法
    {
        System.out.println("抽象类中的具体方法被调用...");
    }
    public abstract void circle(); //抽象方法1
    public abstract void abstractMethod2(); //抽象方法2
}
//具体子类
class ConcreteClass extends AbstractGraph
{
    public void circle()
    {
        System.out.println("抽象方法1的实现被调用...");
    }
    public void abstractMethod2()
    {
        System.out.println("抽象方法2的实现被调用...");
    }
}