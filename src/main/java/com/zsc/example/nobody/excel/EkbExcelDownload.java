package com.zsc.example.nobody.excel;

import com.alibaba.excel.EasyExcel;

import java.io.File;
import java.util.ArrayList;

/**
 * @program: nobody_demo
 * @description: 下载excel
 * @author: zhangsc
 * @create: 2022-08-31 16:52
 * @Copyright: HOSE合思｜易快报
 **/
public class EkbExcelDownload {

    public static void main(String[] args) {
        ArrayList<MarketDataExcel> marketDataExcels = new ArrayList<>();
        EasyExcel.write("/Users/zhangshichuang/Downloads/excelTest.xlsx",MarketDataExcel.class)
                .registerWriteHandler(CellStyleStrategy.styleStrategy())
                .sheet("Market Data").doWrite(marketDataExcels);
    }

}
