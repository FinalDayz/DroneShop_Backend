package com.github.modal;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class Order {

    @NotNull
    private int orderId;

    @NotNull
    private int accountId;

    @NotNull
    private String created;

    public List<ProductOrder> productOrders = new ArrayList<>();

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
