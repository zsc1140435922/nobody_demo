package com.zsc.example.nobody.excel;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2022-09-01 11:52
 * @Copyright: HOSE合思｜易快报
 **/

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

@HeadRowHeight(value = 35)
public class MarketDataExcel {
    @ExcelProperty(value = {"易快报","报销"}, index = 10)
    private String indexCode;

    @ExcelProperty("日期")
    private String date;

    @ExcelProperty("点位")
    private double close;

    public MarketDataExcel() {
    }

    public MarketDataExcel(String indexCode, String date, double close) {
        this.indexCode = indexCode;
        this.date = date;
        this.close = close;
    }

    public String getIndexCode() {
        return indexCode;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }
}