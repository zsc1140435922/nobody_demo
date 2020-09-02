package com.zsc.example.nobody.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-06-19 15:45
 **/
public class ExcelModel extends BaseRowModel {
    @ExcelProperty(index = 0)
    private String supplierId;

    @ExcelProperty(index =1)
    private String shopId;

    @ExcelProperty(index = 2)
    private String mobile;

    /**
     * 获取supplierId
     *
     * @return the supplierId
     */
    public String getSupplierId() {
        return supplierId;
    }

    /**
     * 设置supplierId
     *
     * @param the supplierId
     */
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * 获取shopId
     *
     * @return the shopId
     */
    public String getShopId() {
        return shopId;
    }

    /**
     * 设置shopId
     *
     * @param the shopId
     */
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    /**
     * 获取mobile
     *
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置mobile
     *
     * @param the mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
