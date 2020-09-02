package com.zsc.example.nobody.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class AddExcel {
    public static void main(String[] args) {
        List<Object> exportData = new ArrayList<Object>();
        exportData.add("第一列");
        exportData.add("第二列");
        exportData.add("第三列");
        List<List<Object>> datalist = new ArrayList<List<Object>>();
        List<Object> data = new ArrayList<Object>();
        data.add("111");
        data.add("222");
        data.add("333");
        List<Object> data1 = new ArrayList<Object>();
        data1.add("444");
        data1.add("555");
        data1.add("666");
        datalist.add(data);
        datalist.add(data1);
        String path = "/Users/zhangshichuang/yunzong/exportCsv/";
        String fileName = "文件导出";
        List<Model> l = new ArrayList<>();
        Model model = new Model();
        model.setAge("11");
        model.setName("zsc");
        l.add(model);


        //文件输出位置
        OutputStream out = null;
        try {
            out = new FileOutputStream("/Users/zhangshichuang/yunzong/test.xlsx");
            ExcelWriter writer = EasyExcelFactory.getWriter(out);
            Sheet sheet1 = new Sheet(1, 0);
            sheet1.setSheetName("第一个sheet");
            writer.write(l, sheet1);

            Sheet sheet2 = new Sheet(2, 0, Model.class);
            sheet2.setSheetName("第一个sheet2");
            writer.write(datalist, sheet2);

            writer.finish();

            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }


    static class Model extends BaseRowModel {
        @ExcelProperty(value = "订单编号",index = 0)
        String name;
        @ExcelProperty(value = "订单编号2",index = 1)
        String age;

        /**
         * 获取name
         *
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * 设置name
         *
         * @param the name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * 获取age
         *
         * @return the age
         */
        public String getAge() {
            return age;
        }

        /**
         * 设置age
         *
         * @param the age
         */
        public void setAge(String age) {
            this.age = age;
        }
    }


}
