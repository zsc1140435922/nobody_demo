//package com.zsc.example.nobody.canal;
//
///**
// * @program: nobody_demo
// * @description:
// * @author: zhangsc
// * @create: 2020-08-26 11:07
// **/
//
//import com.alibaba.otter.canal.client.CanalConnector;
//import com.alibaba.otter.canal.client.CanalConnectors;
//import com.alibaba.otter.canal.common.utils.AddressUtils;
//import com.alibaba.otter.canal.protocol.CanalEntry.*;
//import com.alibaba.otter.canal.protocol.Message;
//import com.zsc.example.nobody.date.DateUtils;
//
//import java.net.InetSocketAddress;
//import java.time.LocalDateTime;
//import java.util.List;
//
//
//public class SimpleCanalClientExample02 {
//
//
//    public static void main(String args[]) {
//        // 创建链接
//        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(AddressUtils.getHostIp(),
//                11111), "example", "", "");
////        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("127.0.0.1",11111), "example", "canal", "canal");
//
////        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("",11111), "example", "canal", "canal");
//
////        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("192.168.151.45",11111), "example", "", "");
//
//
//        int batchSize = 2;
//        int emptyCount = 0;
//        try {
//            connector.connect();
//            /**
//             * 全库全表
//             * connector.subscribe(".*\\..*")
//             * 指定库全表
//             * connector.subscribe("test\\..*")
//             * 单表
//             * connector.subscribe("test.user")
//             * 多规则组合使用
//             * connector.subscribe("test\\..*,test2.user1,test3.user2")
//             */
////            connector.subscribe(".*\\..*");
//            connector.subscribe("ekbX3.ekbhello_orderInfo");
//            connector.rollback();
//            int totalEmptyCount = 120;//10000000;//
//            while (emptyCount < totalEmptyCount) {
//                Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
//                long batchId = message.getId();
//                int size = message.getEntries().size();
//                if (batchId == -1 || size == 0) {
//                    emptyCount++;
////                    System.out.println("empty count : " + emptyCount);
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                    }
//                } else {
//                    emptyCount = 0;
//                    DateUtils.localDateTimeFormat(LocalDateTime.now(),DateUtils.FORMAT_PATTERN1);
//                    System.out.printf(DateUtils.localDateTimeFormat(LocalDateTime.now(),DateUtils.FORMAT_PATTERN1)+"  message[batchId=%s,size=%s] \n", batchId, size);
//                    printEntry(message.getEntries());
//                    System.out.printf(DateUtils.localDateTimeFormat(LocalDateTime.now(),DateUtils.FORMAT_PATTERN1)+"  message[batchId=%s,end] \n", batchId);
//                }
//
//                connector.ack(batchId); // 提交确认
//                // connector.rollback(batchId); // 处理失败, 回滚数据
//            }
//
//            System.out.println("暂无数据退出!!!");
//        } finally {
//            connector.disconnect();
//        }
//    }
//
//    private static void printEntry(List<Entry> entrys) {
//        for (Entry entry : entrys) {
//            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
//                continue;
//            }
//
//
//            RowChange rowChage = null;
//            try {
//                rowChage = RowChange.parseFrom(entry.getStoreValue());
//            } catch (Exception e) {
//                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
//                        e);
//            }
//
//            // 可以获取到数据库实例名称、日志文件、当前操作的表以及执行的增删改查的操作
//            EventType eventType = rowChage.getEventType();
//            System.out.println(String.format("================&gt; binlog[%s:%s] , 数据库[%s,%s] , eventType : %s",
//                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
//                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
//                    eventType));
//
//            for (RowData rowData : rowChage.getRowDatasList()) {
//                if (eventType == EventType.DELETE) {
//                    printColumn(rowData.getBeforeColumnsList());
//                } else if (eventType == EventType.INSERT) {
//                    printColumn(rowData.getAfterColumnsList());
//                } else {
//                    System.out.println("-------&gt; before");
//                    printColumn(rowData.getBeforeColumnsList());
//                    System.out.println("-------&gt; after");
//                    printColumn(rowData.getAfterColumnsList());
//                }
//            }
//        }
//    }
//
//    private static void printColumn(List<Column> columns) {
//        for (Column column : columns) {
//            // System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
//        }
//    }
//
//}