package com.zsc.example.nobody.jsrsey;


import java.util.Iterator;

import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2021-10-22 16:46
 **/
public class MyClient
{
    public static void main(String[] args)
    {
        // 创建Jersey Client实例,Client实例很消耗系统资源，需要重用
        ClientConfig cc = new DefaultClientConfig();
        cc.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT, 10*1000);
        Client client = Client.create(cc);

        // 创建web资源,是线程安全的，Client实例和WebResource实例可以在多个线程间安全的共享
        WebResource resource = client.resource("http://127.0.0.1:10000/helloWorld/sayHi?name=cauchy");

        /**
         * 另一种请求方法
         URI uri = UriBuilder.fromUri("http://127.0.0.1/helloWorld/sayHello").port(10000)
         .queryParam("name", "coshaho").build();
         resource = client.resource(uri);
         */

        ClientResponse response = resource.get(ClientResponse.class);
        //        将HTTP响应打印出来
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("HTTP/1.1 ");
        strBuilder.append(response.getStatus() + " ");
        strBuilder.append(response.getStatusInfo());
        System.out.println(strBuilder.toString());
        System.out.println();

        MultivaluedMap<String, String> headers = response.getHeaders();
        Iterator<String> iterator = headers.keySet().iterator();
        while(iterator.hasNext())
        {
            String headName = iterator.next();
            System.out.println(headName + ":" + headers.get(headName));
        }
        System.out.println();

        System.out.println(response.getEntity(String.class));
     }

}