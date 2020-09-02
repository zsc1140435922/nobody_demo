package com.zsc.example.nobody.zookeeper;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.junit.Test;

import java.util.List;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-05-15 12:51
 **/
public class ZkclientDome {
    @Test
    public void testConn() throws InterruptedException {
        ZkClient zkClient = new ZkClient(
                "localhost:2181"
        );

        // 创建节点
        zkClient.createPersistent("/yz/note", true);
//        zkClient.createEphemeral("/yz", "world");
//        String key = zkClient.create("/yz/note", "world", CreateMode.EPHEMERAL_SEQUENTIAL);
//        String key1 = zkClient.create("/yz/note", "world", CreateMode.EPHEMERAL_SEQUENTIAL);
//        String key2 = zkClient.create("/yz/note", "world", CreateMode.EPHEMERAL_SEQUENTIAL);
        String path2 = zkClient.create("/yz/note/", "yzzsc".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        String path3 = zkClient.create("/yz/note/", "yzzsc".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        String path4 = zkClient.create("/yz/note/", "yzzsc".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        String data = zkClient.readData("/yz");
        System.out.println(data);
//        String data1 = zkClient.readData("/yz");
//        System.out.println(data1);

        // 监听状态变化
        zkClient.subscribeStateChanges(new IZkStateListener() {
            @Override
            public void handleStateChanged(Watcher.Event.KeeperState keeperState) throws Exception {
                System.out.println("state:" + keeperState);
            }

            @Override
            public void handleNewSession() throws Exception {
                System.out.println("new session");
            }

            @Override
            public void handleSessionEstablishmentError(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });

        // 监听子节点发生变化
        zkClient.subscribeChildChanges("/yz/note", new IZkChildListener() {
            @Override
            public void handleChildChange(String path, List<String> list) throws Exception {
                System.out.println("watch path:" + path);
                // 输出所有子节点
                list.forEach(str -> {
                    System.out.println(str);
                });
            }
        });

        Thread.sleep(10000000);
    }
}
