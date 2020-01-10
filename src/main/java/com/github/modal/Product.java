package com.github.modal;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class Product {

    @NotNull
    private int productId;

    @NotNull
    @Length(max = 100)
    private String productName;

    @NotNull
    private String productDesc;

    @NotNull
    private double productPrice;

    @Length(max = 100)
    private String productImagePath;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImagePath() {
        return productImagePath;
    }

    public void setProductImagePath(String productImagePath) {
        this.productImagePath = productImagePath;
    }
}
