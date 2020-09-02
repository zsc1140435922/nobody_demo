package com.zsc.example.nobody.classreflection;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-03-05 15:07
 **/
public class User {

    private String name;
    private Integer age;

    private String sex;

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
    public Integer getAge() {
        return age;
    }

    /**
     * 设置age
     *
     * @param the age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取sex
     *
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置sex
     *
     * @param the sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }
}
