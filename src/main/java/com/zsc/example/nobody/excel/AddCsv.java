package com.zsc.example.nobody.excel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-07-18 19:53
 **/
public class AddCsv {
    /**
     * CSV文件生成方法
     * @param head 文件头
     * @param dataList 数据列表
     * @param outPutPath 文件输出路径
     * @param filename 文件名
     * @return
     */
    public static File createCSVFile(List<Object> head, List<List<Object>> dataList, String outPutPath, String filename) {

        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            csvFile = new File(outPutPath + File.separator + filename + ".csv");
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            if(!csvFile.exists()){
                csvFile.createNewFile();
            }

            // GB2312使正确读取分隔符","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    csvFile), "GB2312"), 1024);
            // 写入文件头部
            writeRow(head, csvWtriter);

            // 写入文件内容
            for (List<Object> row : dataList) {
                writeRow(row, csvWtriter);
            }
            csvWtriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvWtriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }

    /**
     * 写一行数据方法
     * @param row
     * @param csvWriter
     * @throws IOException
     */
    private static void writeRow(List<Object> row, BufferedWriter csvWriter) throws IOException {
        // 写入文件头部
        for (Object data : row) {
            StringBuffer sb = new StringBuffer();
            String rowStr = sb.append("\"").append(data).append("\",").toString();
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }




    public static void main(String[] args) {
        List<Object> exportData = new ArrayList<Object>();
        exportData.add("第一列");
        exportData.add("第二列");
        exportData.add("第三列");
        List<List<Object>> datalist = new ArrayList<List<Object>>();
        List<Object> data=new ArrayList<Object>();
        data.add("111");
        data.add("222");
        data.add("333");
        List<Object> data1=new ArrayList<Object>();
        data1.add("444");
        data1.add("555");
        data1.add("666");
        datalist.add(data);
        datalist.add(data1);
        String path = "/Users/zhangshichuang/yunzong/exportCsv/";
        String fileName = "文件导出";
        File file = createCSVFile(exportData, datalist, path, fileName);
        File file1 = createCSVFile(exportData, datalist, path, "文件2");
        String fileName2 = file.getName();
        System.out.println("文件名称：" + fileName2);
    }
}
