package com.zsc.example.nobody.diff;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2021-11-19 16:03
 **/
public class Person {
    private Integer id;
    private String name;

    Person(Integer id, String name){
        this.id =id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
