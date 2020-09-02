package com.zsc.example.nobody.classreflection;

import java.util.List;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-03-02 11:28
 **/
public class ObjectTest {
    private String message;
    private String returnFlag;
    private String visible;
    private List<ChildObject> list;
    private ChildObject childObject;
    private int number;

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getReturnFlag() {
        return returnFlag;
    }
    public void setReturnFlag(String returnFlag) {
        this.returnFlag = returnFlag;
    }
    public String getVisible() {
        return visible;
    }
    public void setVisible(String visible) {
        this.visible = visible;
    }
    public List<ChildObject> getList() {
        return list;
    }
    public void setList(List<ChildObject> list) {
        this.list = list;
    }
    public ChildObject getChildObject() {
        return childObject;
    }
    public void setChildObject(ChildObject childObject) {
        this.childObject = childObject;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public static void main(String[] args) {
        String[] clazzTypes = String.class.toString().split("\\.");
        System.out.println(clazzTypes[clazzTypes.length-1]);
        System.out.println(Integer.class);
        System.out.println(Object.class);
        System.out.println(ObjectTest.class);
    }
}
