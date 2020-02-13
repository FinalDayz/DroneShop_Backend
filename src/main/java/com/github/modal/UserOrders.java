package com.github.modal;

import javax.validation.constraints.NotNull;

public class UserOrders {

    @NotNull
    private int accountId;

    @NotNull
    private int[] products;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int[] getProducts() {
        return products;
    }

    public void setProducts(int[] products) {
        this.products = products;
    }
}
