package com.zsc.example.nobody.zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-05-10 23:01
 **/
public class Zkclient {
    public static void main(String[] args) throws Exception{
        ZkClient zkClient = new ZkClient("localhost:2181");//建立连接
        zkClient.create("/root2","", CreateMode.PERSISTENT);//创建目录并写入数据
        String data=zkClient.readData("/root2");
        System.out.println(data);
        boolean boo = zkClient.delete("/yz/note/0000000016");//删除目录
//        zkClient.deleteRecursive("/root");//递归删除节目录

        System.out.println(boo);
//        ZooKeeper zk = new ZooKeeper("localhost:2181",
//                9999, new Watcher() {
//            // 监控所有被触发的事件
//            @Override
//            public void process(WatchedEvent event) {
//                System.out.println("已经触发了" + event.getType() + "事件！");
//            }
//        });
    }
}
