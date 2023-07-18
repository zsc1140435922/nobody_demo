package com.zsc.example.nobody.jsrsey;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.glassfish.grizzly.http.server.HttpServer;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.spi.resource.Singleton;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2021-10-22 16:44
 **/

@Singleton             //@Singleton表示单例模式，@PerRequest每个请求对应一个实例，默认为@PerRequest
@Path("helloWorld")    //服务名称，紧接port/
public class MyServer {
    @Path("{sub_path:[a-zA-Z]*}")       //服务子路径，可以使用正则表达式，表示响应子路径为任意字母的请求
    @GET                                //表示接受HTTP GET请求，@POST、@PUT和@DELETE同理
    @Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})         //接受请求的媒体类型（MIME），不指定则可以接受任务媒体类型
    @Produces(MediaType.TEXT_PLAIN)                                       //定义响应媒体类型（MIME），不指定则可以接受任务媒体类型
    public String sayHelloWorld(
            // 使用路径定义中的sub_path变量
            @PathParam("sub_path") String service,

            // @QueryParam表示接受请求中的参数，@DefaultValue为默认值
            @DefaultValue("coshaho") @QueryParam("name") String name,

            // @Context可以获取请求上下文，包括Application，UriInfo，Request，HttpHeaders，SecurityContext
            @Context Request request,
            @Context UriInfo uriInfo,
            @Context HttpHeaders httpHeader
    ) {
        System.out.println("Sub path is " + service + ".");
        System.out.println("Name is " + name + ".");
        System.out.println("Request method is " + request.getMethod() + ", url is " + uriInfo.getRequestUri().toString() + ".");
        return "Hello, " + name + ". Service is " + service + ".";
    }

    public static void main(String[] args) {
        URI uri = UriBuilder.fromUri("http://127.0.0.1").port(10000).build();
        ResourceConfig rc = new PackagesResourceConfig("com.zsc.example.nobody.jsrsey");
        try {
            HttpServer server = GrizzlyServerFactory.createHttpServer(uri, rc);
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(1000 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}