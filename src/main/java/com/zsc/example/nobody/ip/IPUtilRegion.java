package com.zsc.example.nobody.ip;

import com.zsc.example.nobody.id.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;

import java.io.File;
import java.lang.reflect.Method;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-01-07 11:22
 **/
public class IPUtilRegion {
    public static String getCityInfo(String ip){
        //db
        String dbPath = IPUtilRegion.class.getResource("/ip2region.db").getPath();

        File file = new File(dbPath);

        if ( file.exists() == false ) {
            System.out.println("Error: Invalid ip2region.db file");
        }

        //查询算法
        int algorithm = DbSearcher.BTREE_ALGORITHM; //B-tree
        //DbSearcher.BINARY_ALGORITHM //Binary
        //DbSearcher.MEMORY_ALGORITYM //Memory
        try {
            DbConfig config = new DbConfig();
            DbSearcher searcher = new DbSearcher(config, dbPath);

            //define the method
            Method method = null;
            switch ( algorithm )
            {
                case DbSearcher.BTREE_ALGORITHM:
                    method = searcher.getClass().getMethod("btreeSearch", String.class);
                    break;
                case DbSearcher.BINARY_ALGORITHM:
                    method = searcher.getClass().getMethod("binarySearch", String.class);
                    break;
                case DbSearcher.MEMORY_ALGORITYM:
                    method = searcher.getClass().getMethod("memorySearch", String.class);
                    break;
            }

            DataBlock dataBlock = null;
            if ( Util.isIpAddress(ip) == false ) {
                System.out.println("Error: Invalid ip address");
            }

            dataBlock  = (DataBlock) method.invoke(searcher, ip);

            return dataBlock.getRegion();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        for (int i=0;i<10;i++) {

            //这里因为是去读取本地的纯真库，所以有一个IO异常抛出
            String ip = IdWorker.RandomIp.getRandomIp();
            String str = getCityInfo(ip);
            String [] a = str.split("\\|");
            if (StringUtils.isNoneBlank(str)){
                System.out.println(ip + "---"+a[3]);
            } else{
                System.out.println(ip);
            }


//            System.out.println(zone.getSubInfo());
        }
        System.out.println(System.currentTimeMillis()-time);
    }
}
