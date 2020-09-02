package com.zsc.example.nobody.interceptor;

import java.io.Serializable;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-01-02 14:06
 **/
public class SinaIpVo implements Serializable {
    private Integer ret;
    private String province;
    private String city;

    /**
     * 获取ret
     *
     * @return the ret
     */
    public Integer getRet() {
        return ret;
    }

    /**
     * 设置ret
     *
     * @param the ret
     */
    public void setRet(Integer ret) {
        this.ret = ret;
    }

    /**
     * 获取province
     *
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置province
     *
     * @param the province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取city
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置city
     *
     * @param the city
     */
    public void setCity(String city) {
        this.city = city;
    }
}
