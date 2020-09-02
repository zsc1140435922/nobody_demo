package com.zsc.example.nobody.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-05-15 15:05
 **/
public class LeaderSelectorDome {
    public static void main(String[] args) throws Exception {
        String zookeeperAddr = "127.0.0.1:2181";
//zk的重连策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
//获取连接
        CuratorFramework client = CuratorFrameworkFactory.newClient(zookeeperAddr, retryPolicy);
        client.start();

        String path = "/newserver/leader";  //选举的节点信息放在这个path下

//这里建议使用LeaderSelectorListenerAdapter，它实现了stateChanged，当与zk失连后，会自动取消领导权
        LeaderSelector leaderSelector = new LeaderSelector(client, path, new LeaderSelectorListenerAdapter() {

            @Override
            public void takeLeadership(CuratorFramework client) throws Exception {
                System.out.println("1111成为leader了");

                Thread.sleep(10000);  //sleep 10秒
                //注意当takeLeadership方法返回之后，相当于放弃成为leader了
                System.out.println("1111放弃成为leader");
            }

        });

//放弃领导权之后，自动再次竞选
        leaderSelector.autoRequeue();
        leaderSelector.start();

        TimeUnit.HOURS.sleep(1);
    }
}
