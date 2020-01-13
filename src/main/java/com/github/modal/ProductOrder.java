package com.github.modal;

import javax.validation.constraints.NotNull;

public class ProductOrder {
    @NotNull
    private int orderId;

    @NotNull
    private int productId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
