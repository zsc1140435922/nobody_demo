package com.zsc.example.nobody.canal;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-08-26 11:07
 **/

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.common.utils.AddressUtils;
import com.alibaba.otter.canal.protocol.CanalEntry.*;
import com.alibaba.otter.canal.protocol.Message;
import com.zsc.example.nobody.date.DateUtils;

import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class SimpleCanalClientExample {


    public static void main(String args[]) {
        // 创建链接
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(AddressUtils.getHostIp(),
                11111), "example", "", "");
        int batchSize = 1000;
        try {
            connector.connect();
            /**
             * 全库全表
             * connector.subscribe(".*\\..*")
             * 指定库全表
             * connector.subscribe("test\\..*")
             * 单表
             * connector.subscribe("test.user")
             * 多规则组合使用
             * connector.subscribe("test\\..*,test2.user1,test3.user2")
             */
            connector.subscribe("ekbX3.ekbhello_orderInfo");
            connector.rollback();

            while (true) {
                Message message = connector.getWithoutAck(batchSize, 100L, TimeUnit.MILLISECONDS);
                int size = message.isRaw() ? message.getRawEntries().size() : message.getEntries().size();
                long batchId = message.getId();
                System.out.println(DateUtils.getDateTime()+" start batchId:" +batchId);
                if (batchId != -1 && size != 0) {
                    printEntry(message.getEntries());
                }
                System.out.println(DateUtils.getDateTime()+" end batchId:" +batchId);
                connector.ack(batchId); // 提交确认
                //connector.rollback(batchId); // 处理失败, 回滚数据
            }

        } finally {
            connector.disconnect();
        }
    }

    private static void printEntry(List<Entry> entrys) {
        for (Entry entry : entrys) {
            if (EntryType.ROWDATA == entry.getEntryType()) {
                RowChange rowChage = null;
                try {
                    rowChage = RowChange.parseFrom(entry.getStoreValue());
                } catch (Exception e) {
                    throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                            e);
                }

                EventType eventType = rowChage.getEventType();
                for (RowData rowData : rowChage.getRowDatasList()) {
                    if (eventType == EventType.DELETE) {
                        printColumn(rowData.getBeforeColumnsList());
                    } else if (eventType == EventType.INSERT) {
                        printColumn(rowData.getAfterColumnsList());
                    } else {
                        //printColumn(rowData.getBeforeColumnsList());
                        printColumn(rowData.getAfterColumnsList());
                    }
                }
            }
        }
    }

    private static void printColumn(List<Column> columns) {
        System.out.println(DateUtils.getDateTime() + " id:" + columns.get(0).getValue());
//        for (Column column : columns) {
//             System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
//        }
    }

}