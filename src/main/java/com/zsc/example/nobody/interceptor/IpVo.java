package com.zsc.example.nobody.interceptor;

import java.io.Serializable;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-01-02 14:06
 **/
public class IpVo implements Serializable{
    private Integer code;
    private Address address;

    public class Address implements Serializable {
        private String ip;
        private String region;
        private String city;

        /**
         * 获取ip
         *
         * @return the ip
         */
        public String getIp() {
            return ip;
        }

        /**
         * 设置ip
         *
         * @param the ip
         */
        public void setIp(String ip) {
            this.ip = ip;
        }

        /**
         * 获取region
         *
         * @return the region
         */
        public String getRegion() {
            return region;
        }

        /**
         * 设置region
         *
         * @param the region
         */
        public void setRegion(String region) {
            this.region = region;
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

    /**
     * 获取code
     *
     * @return the code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 设置code
     *
     * @param the code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 获取address
     *
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * 设置address
     *
     * @param the address
     */
    public void setAddress(Address address) {
        this.address = address;
    }
}