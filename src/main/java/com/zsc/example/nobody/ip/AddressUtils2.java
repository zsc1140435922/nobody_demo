package com.zsc.example.nobody.ip;


import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.zsc.example.nobody.id.IdWorker;

import java.io.File;
import java.net.InetAddress;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-01-02 13:39
 **/
public class AddressUtils2 {
    public static  DatabaseReader reader = null;
    public static void main(String[] args) throws Exception {


        long time = System.currentTimeMillis();
        // 获取查询结果
        for (int i=0;i<100;i++) {

            CityResponse response =  getIPAddress(IdWorker.RandomIp.getRandomIp());
            // 获取国家信息
//            Country country = response.getCountry();
//            System.out.println(country.getIsoCode());               // 'CN'
//            System.out.println(country.getName());                  // 'China'
//            System.out.println(country.getNames().get("zh-CN"));    // '中国'
//
//            // 获取省份
//            Subdivision subdivision = response.getMostSpecificSubdivision();
//            System.out.println(subdivision.getName());   // 'Guangxi Zhuangzu Zizhiqu'
//            System.out.println(subdivision.getIsoCode()); // '45'
//            System.out.println(subdivision.getNames().get("zh-CN")); // '广西壮族自治区'
//
//            // 获取城市
//            City city = response.getCity();
//            System.out.println(city.getName()); // 'Nanning'
//            Postal postal = response.getPostal();
//            System.out.println(postal.getCode()); // 'null'
//            System.out.println(city.getNames().get("zh-CN")); // '南宁'
//            Location location = response.getLocation();
//            System.out.println(location.getLatitude());  // 22.8167
//            System.out.println(location.getLongitude()); // 108.3167
        }
        System.out.println(System.currentTimeMillis()-time);
    }


    public static CityResponse getIPAddress(String ip) throws Exception {
        String url = AddressUtils2.class.getResource("/").getPath();
        String path = System.getProperty("user.dir");
//        File database = new File("//Users/zhangshichuang/yunzong/GeoLite2-City.mmdb");
        if (reader == null){
            File database = new File(url + "/GeoLite2-City.mmdb");
            // 读取数据库内容
            reader = new DatabaseReader.Builder(database).build();
        }
        InetAddress ipAddress = InetAddress.getByName(ip);//125.70.11.136  10.100.30.55
        return reader.city(ipAddress);
//     // 获取查询结果
//     CityResponse response = reader.city(ipAddress);
//
//     // 获取国家信息
//     Country country = response.getCountry();
//     System.out.println(country.getIsoCode());               // 'CN'
//     System.out.println(country.getName());                  // 'China'
//     System.out.println(country.getNames().get("zh-CN"));    // '中国'
//
//     // 获取省份
//     Subdivision subdivision = response.getMostSpecificSubdivision();
//     System.out.println(subdivision.getName());   // 'Guangxi Zhuangzu Zizhiqu'
//     System.out.println(subdivision.getIsoCode()); // '45'
//     System.out.println(subdivision.getNames().get("zh-CN")); // '广西壮族自治区'
//
//     // 获取城市
//     City city = response.getCity();
//     System.out.println(city.getName()); // 'Nanning'
//     Postal postal = response.getPostal();
//     System.out.println(postal.getCode()); // 'null'
//     System.out.println(city.getNames().get("zh-CN")); // '南宁'
//     Location location = response.getLocation();
//     System.out.println(location.getLatitude());  // 22.8167
//     System.out.println(location.getLongitude()); // 108.3167

    }
}
