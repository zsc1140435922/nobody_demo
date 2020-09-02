package com.zsc.example.nobody.excel;

import com.zsc.example.nobody.uuid.UniqId;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-06-19 14:53
 **/
@SpringBootApplication
public class ExcelDemo {

    private String insertsql1 = "INSERT INTO `yz_pro_supplier_service_order`(" +
            "`order_id`,`supplier_id`,`order_type`,`service_version_id`,`service_version_type`," +
            "`machine_tool_nums`,`pay_type`,`third_user_id`,`third_trade_time`,`third_trade_no`," +
            "`biz_no`,`status`,`create_time`,`update_time`,`boss_phone`,`order_source`)" +
            " VALUES('%s','%s','5','1','1','1','1','free',unix_timestamp(),'free','free',2,unix_timestamp(),unix_timestamp(),'%s',8);";

    private String insertsql2 = "INSERT INTO `yz_pro_supplier_service_order_detail`(" +
            "`order_detail_id`,`order_id`,`supplier_id`,`package_id`,`package_type`,`machine_tool_type`,`status`,`price`,`create_time`,`update_time`)" +
            "VALUES('%s','%s','%s',33,33,33,2,1999.00,unix_timestamp(),unix_timestamp());";

    private String insertsql3 = "INSERT INTO `yz_pro_supplier_service_order_history`(`id`,`order_id`,`supplier_id`,`status`,`create_time`,`update_time`)" +
            "VALUES('%s','%s','%s',2,unix_timestamp(),unix_timestamp());";


    private String insertsql4 = "INSERT INTO `yz_pro_shop_service`(`shop_id`,`supplier_id`,`service_code`,`service_status`,`create_time`,`update_time`)VALUES" +
            "('%s','%s',9,'1_3',unix_timestamp(),unix_timestamp());";

    private String insertsql5 = "INSERT INTO `yz_pro_supplier_function_module`(`id`,`supplier_id`,`fm_id`,`fm_name`,`fm_desc`,`create_time`,`update_time`)VALUES" +
            "('%s','%s','MARKETING_SERVICE','营销服务订购','营销服务订购',unix_timestamp(),unix_timestamp());";

    @Test
    public void testReadExcel() {
        ExcelOptionsService service = new ExcelOptionsService();
//        try{
//            // 这里的excel文件可以 为xls或xlsx结尾
//            File file = new File("/Users/zhangshichuang/Downloads/活跃商户数据.xlsx");
//            List<List<String>> result = new ArrayList<>();
//            try {
//                result = service.writeWithoutHead(new FileInputStream(file));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//
//        }catch (Exception e){
//
//        }

        Set<String> set = new HashSet<>();

        try {
            // 这里的excel文件可以 为xls或xlsx结尾
            File file = new File("/Users/zhangshichuang/Downloads/活跃商户数据.xlsx");
            List<ExcelModel> result = new ArrayList<>();
            try {
                result = service.writeWithoutHead1(new FileInputStream(file));
                List<String> resultList = new ArrayList<>();
                if (CollectionUtils.isNotEmpty(result)) {
                    for (ExcelModel item : result) {
                        String orderid = UniqId.getInstance().getUniqID29();
                        String supplierId = item.getSupplierId();
                        String shopId = item.getShopId();
                        String mobile = item.getMobile();
                        String orderDetailId = UniqId.getInstance().getUniqID29();
                        String id = UniqId.getInstance().getUniqID29();

                        String s1 = String.format(insertsql1, orderid, supplierId, mobile);
                        String s2 = String.format(insertsql2, orderDetailId, orderid, supplierId);
                        String s3 = String.format(insertsql3, id, orderid, supplierId);

                        String id1 = UniqId.getInstance().getUniqID29();
                        resultList.add(s1);
                        resultList.add(s2);
                        resultList.add(s3);

                        if (!set.contains(supplierId)) {
                            String s4 = String.format(insertsql4, shopId, supplierId);
                            String s5 = String.format(insertsql5, id1, supplierId);
                            resultList.add(s4);
                            resultList.add(s5);
                            set.add(supplierId);
                        }

                        /**
                         * true, 追加
                         */
                        FileUtils.writeLines(new File("/Users/zhangshichuang/yunzong/insertSql.txt"), resultList, true);
                        resultList.clear();
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
