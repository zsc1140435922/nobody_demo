package com.zsc.example.nobody.ip;

import com.alibaba.fastjson.JSON;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.*;
import com.zsc.example.nobody.id.IdWorker;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-01-06 10:51
 **/
public class IpTest {
    public static void main(String[] args) throws  Exception{
        long time = System.currentTimeMillis();
        int a =0;
        int b=0;
        // 获取查询结果
        for (int i=0;i<1;i++) {
            String ip = IdWorker.RandomIp.getRandomIp();
            CityResponse response = AddressUtils2.getIPAddress("210.32.45.134");
            Country country = response.getCountry();

            Subdivision subdivision = response.getMostSpecificSubdivision();
            City city = response.getCity();
            if (city.getNames().size() > 0){
                a++;
                System.out.println(ip +"---" +  JSON.toJSON(response));
            } else {
                System.out.println(ip +"=" +  JSON.toJSON(response));
                b++;
            }
            Postal postal = response.getPostal();

            Location location = response.getLocation();
//            CityResponse response = AddressUtils2.getIPAddress(RandomIp.getRandomIp());

        }
        System.out.println(System.currentTimeMillis()-time);
        System.out.println("a="+a +";b="+b);
    }
}
