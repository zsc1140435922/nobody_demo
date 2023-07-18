package com.zsc.example.nobody.jsrsey;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.core.spi.scanning.PackageNamesScanner;
import com.sun.jersey.core.spi.scanning.Scanner;
import com.sun.jersey.spi.scanning.AnnotationScannerListener;
import com.sun.jersey.spi.scanning.PathProviderScannerListener;
import javafx.util.Builder;

import javax.ws.rs.core.MediaType;
import java.util.Set;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2021-10-22 15:35
 *
 * Jersey 中自带一个包扫描，可以是包，或者具体类名 ，扫描的类型是自己定注解类型，实现功能更加大，可以是jar 包 可以是虚拟地址下的
 *
 * Jersey 主要用来扫描Path Provider 类中同时包括以上的Annotation,使用Jersey 包扫描只需要三步。
 *
 * 1、包地址传入PackageNamesScanner
 * 2、自定义的注解实现AnnotationScannerListener 的子类
 * 3、scan
 **/
public class JsrseyDemo {
    public static void main(String[] args)
    {
        String aa[] = new String[]{"com.zsc.example.nobody.jsrsey"};
        Scanner scanner =  new PackageNamesScanner(checkNotNull(aa));
        final AnnotationScannerListener asl = new PathProviderScannerListener();
        final AnnotationScannerListener asl1 = new ErrInfoScannerListener();
        scanner.scan(asl1);
        scanner.scan(asl);
        Set<Class<?>> sets1 = asl1.getAnnotatedClasses();
        Set<Class<?>> sets=  asl.getAnnotatedClasses();
        System.out.println(sets);


//        String url = "http://192.168.1.134:8080/MyJersey/rest";
//        Client client = ClientBuilder.newClient();
//        WebTarget webTarget = client.target(url).path("HelloWorld").path("sayHello/admin");
//        Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
//        String result = builder.get(String.class);
//        System.out.println(result);

    }
}
