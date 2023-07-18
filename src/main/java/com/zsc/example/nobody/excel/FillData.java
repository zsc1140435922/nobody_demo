package com.zsc.example.nobody.excel;

import jdk.nashorn.internal.objects.annotations.Setter;

import java.util.Date;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2022-09-02 18:41
 * @Copyright: HOSE合思｜易快报
 **/

public class FillData {
    private String name;
    private double number;
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}