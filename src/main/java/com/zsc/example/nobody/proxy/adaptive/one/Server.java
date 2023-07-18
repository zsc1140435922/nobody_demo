package com.zsc.example.nobody.proxy.adaptive.one;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2021-06-21 10:17
 **/

/**
 *  * 网站服务器
 *  * 需要Tomcat容器，Mysql数据库，网络服务，远程服务
 *  
 */
public class Server extends Wrapper {
    @Override
    public void SSH() {
        System.out.println("Connect success!");
    }
}
