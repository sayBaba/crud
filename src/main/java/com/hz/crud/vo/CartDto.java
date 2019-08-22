package com.hz.crud.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartDto implements Serializable {

    private String productId;
    private BigDecimal productPrice;
    private Integer productNum;
    /*是否勾选*/
    private String check;
    private String productName;
    /** 状态, 0正常1下架. */
    private Integer productStatus;

    /* 商品小图*/
    private String productIcon;

    public String getCheck() {
        return check;
    }

    public void setCheck(String checkAll) {
        this.check = checkAll;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductIcon() {
        return productIcon;
    }

    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }
}