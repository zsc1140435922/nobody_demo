package com.zsc.example.nobody.proxy.fastClass;

/**
 * @program: nobody_demo
 * @description: 委托类
 * @author: zhangsc
 * @create: 2020-06-02 14:50
 **/
public class DelegateClass {
    public String name;
    public DelegateClass() {
    }

    public DelegateClass(String string) {
        this.name = string;
    }

    public boolean add(String string, int i) {
        System.out.println(name);
        System.out.println("This is add method: " + string + ", " + i);
        return true;
    }

    public void update() {
        System.out.println("This is update method");
    }
}
