package com.zsc.example.nobody.excel;



import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.*;
/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2022-09-01 11:52
 * @Copyright: HOSE合思｜易快报
 **/
public class CellStyleStrategy {
    /**
     * 设置表头和内容样式
     */

    public static HorizontalCellStyleStrategy styleStrategy() {
        //表头样式策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        //设置表头居中对齐
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);

        headWriteCellStyle.setLeftBorderColor(IndexedColors.GREY_25_PERCENT.index);
        headWriteCellStyle.setRightBorderColor(IndexedColors.GREY_25_PERCENT.index);
        headWriteCellStyle.setBottomBorderColor(IndexedColors.GREY_25_PERCENT.index);
        headWriteCellStyle.setTopBorderColor(IndexedColors.GREY_25_PERCENT.index);

        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setBold(false);
        headWriteFont.setFontName("Arial");
        headWriteFont.setFontHeightInPoints((short) 10);
        headWriteCellStyle.setWriteFont(headWriteFont);

        //2 内容样式策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        WriteFont contentWriteFont = new WriteFont();
        //内容大小
        contentWriteFont.setFontName("Arial");
        contentWriteFont.setFontHeightInPoints((short) 10);
        contentWriteCellStyle.setWriteFont(contentWriteFont);

//        //设置自动换行
//        contentWriteCellStyle.setWrapped(true);
//        //设置垂直居中
//        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//        //头默认了FillPatternType所以可以不指定
//        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
//        //设置水平居中
//        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
//
//        //设置边框样式
//        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
//        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
//        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
//        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);

        return new HorizontalCellStyleStrategy(headWriteCellStyle,contentWriteCellStyle);
    }
}