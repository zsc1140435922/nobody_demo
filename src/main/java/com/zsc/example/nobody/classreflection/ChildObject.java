package com.zsc.example.nobody.classreflection;

import java.util.List;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-03-02 11:28
 **/
public class ChildObject {
    private String childName;
    private String childValue;
    private String address;
    private List<ChildObject> childList;


    public String getChildName() {
        return childName;
    }
    public void setChildName(String childName) {
        this.childName = childName;
    }
    public String getChildValue() {
        return childValue;
    }
    public void setChildValue(String childValue) {
        this.childValue = childValue;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public List<ChildObject> getChildList() {
        return childList;
    }
    public void setChildList(List<ChildObject> childList) {
        this.childList = childList;
    }
}
