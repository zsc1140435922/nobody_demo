package com.zsc.example.nobody.ip;

import com.github.jarod.qqwry.IPZone;
import com.github.jarod.qqwry.QQWry;
import com.zsc.example.nobody.id.IdWorker;

import java.io.IOException;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-01-06 13:01
 **/
public class QQTest {
    public static void main(String[] args) {
        QQWry wry = null;
        try {
            wry = new QQWry();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long time = System.currentTimeMillis();
        for (int i=0;i<1;i++) {

            //这里因为是去读取本地的纯真库，所以有一个IO异常抛出
            String ip = IdWorker.RandomIp.getRandomIp();
            IPZone zone = wry.findIP("123.235.101.137");
            if (!"区域网".equals(zone.getMainInfo())){
                String str = zone.getMainInfo();
                if(str.contains("市")){

                }
//                QQWryIPUtil.

            }
            System.out.println(ip + ":"+zone.getMainInfo());
//            System.out.println(zone.getSubInfo());
        }
        System.out.println(System.currentTimeMillis()-time);
    }


}
