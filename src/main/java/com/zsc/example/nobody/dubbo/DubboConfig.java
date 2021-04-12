package com.zsc.example.nobody.dubbo;

import org.apache.dubbo.config.*;

import java.util.concurrent.CountDownLatch;

/**
 * @program: nobody_demo
 * @description: dubbo配置
 * @author: zhangsc
 * @create: 2021-02-05 11:33
 **/
public class DubboConfig {
    /*** dubbo的服务暴露 */
    public void server() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("dubbo-server-config");
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://ds.zk1-1.yunzong:2181");
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20880);
        protocolConfig.setThreads(200);
        UserService userService = new UserServiceImpl();
        ServiceConfig<UserService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setInterface(UserService.class);
        serviceConfig.setRef(userService);
        serviceConfig.export();
    }

    public void consumer() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("dubbo-client-config");
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://ds.zk1-1.yunzong:2181");
        ReferenceConfig<UserService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(UserService.class);
        UserService localRef = referenceConfig.get();
        System.out.println(localRef.getName("idea"));
    }

    public static void main(String[] args) throws InterruptedException {
        DubboConfig d = new DubboConfig();
        d.server();
        d.consumer();
//        CountDownLatch countDownLatch = new CountDownLatch(1);
//        countDownLatch.await();
    }

}
