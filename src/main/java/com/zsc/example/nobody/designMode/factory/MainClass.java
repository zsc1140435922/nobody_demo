package com.zsc.example.nobody.designMode.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-04-21 22:52
 **/
public class MainClass {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        //1
        GraphFactory.getGraph("com.zsc.example.nobody.designMode.factory.Circle").draw();
        GraphFactory.getGraph("com.zsc.example.nobody.designMode.factory.Square").draw();
        GraphFactory.getGraph("com.zsc.example.nobody.designMode.factory.Triangle").draw();
        //2
        //3
        List<String> list = new ArrayList<>(3);
        list.add("com.zsc.example.nobody.designMode.factory.Circle");
        list.add("com.zsc.example.nobody.designMode.factory.Square");
        list.add("com.zsc.example.nobody.designMode.factory.Triangle");
        for (String _class: list){
            GraphFactory.getGraph(_class).draw();
        }
        list.forEach(_class->{
            try {
                GraphFactory.getGraph(_class).draw();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        });

        //4  加入 Rhombus
        GraphFactory.getGraph("com.zsc.example.nobody.designMode.factory.Rhombus").draw();

    }
}
