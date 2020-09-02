package com.zsc.example.nobody.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-05-15 14:56
 **/
public class LeaderLatchDome {
    public static void main(String[] args) throws Exception {
        String zookeeperAddr = "localhost:2181";
//重连策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
//获取连接
        CuratorFramework client = CuratorFrameworkFactory.newClient(zookeeperAddr, retryPolicy);
        client.start();

        String path = "/newserver/leader";
        LeaderLatch leaderLatch = new LeaderLatch(client, path);
        leaderLatch.start();
        leaderLatch.await();//阻塞等待自己有领导权
        System.out.println("成为leader了");
        new BufferedReader(new InputStreamReader(System.in)).readLine();//输入参数后，放弃领导权

        leaderLatch.close();
        System.out.println("放弃成为leader，当前是否有领导权：" + leaderLatch.hasLeadership());
    }
}
