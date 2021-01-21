package com.zsc.example.nobody.stream;


import java.io.Serializable;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-06-22 18:38
 **/
public class Student implements Serializable {

    private String name;
    private int age;
    private int type;
    private Integer score;
    Student(){}
    Student(String name, int age ){
        this.name = name;
        this.age = age;
    }
    public Student(String name, int age, int type){
        this.name = name;
        this.age = age;

        this.type = type;
    }
    public Student(String name, int age, int  type, int score){
        this.name = name;
        this.age = age;
        this.type = type;
        this.score = score;
    }

    /**
     * 获取name
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name
     *
     * @param the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取age
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置age
     *
     * @param the age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 获取type
     *
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * 设置type
     *
     * @param the type
     */
    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", type=" + type +
                ", score=" + score +
                '}';
    }

    /**
     * 获取score
     *
     * @return the score
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 设置score
     *
     * @param the score
     */
    public void setScore(Integer score) {
        this.score = score;
    }
}
