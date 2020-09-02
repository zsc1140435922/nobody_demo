package com.zsc.example.nobody.proxy.jdk_cglib;

/**
 * @program: nobody_demo
 * @description: jdk_cglib
 * @author: zhangsc
 * @create: 2020-05-29 16:28
 **/
public class CglibPersonService {

    public String savePerson() {
        System.out.println("添加");
        return "保存成功！";
    }

    public void updatePerson() {
        System.out.println("修改");
    }

    public void deletePerson() {
        System.out.println("删除");
    }


}

