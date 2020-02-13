package com.github.service;

import com.github.modal.Order;
import com.github.modal.ProductOrder;
import com.github.modal.UserOrders;
import com.github.persistence.OrderDAO;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.ws.rs.core.Response;
import java.util.Arrays;

public class OrderService extends Service<OrderDAO> {
    public OrderService(OrderDAO DAO) {
        super(DAO);
    }

    public Response processOrder(UserOrders userOrders) {
        Order order = new Order();
        order.setAccountId(userOrders.getAccountId());
        int orderId = this.DAO.addOrder(order);

        for(int i = 0; i < userOrders.getProducts().length; i++) {
            ProductOrder productOrder = new ProductOrder();
            productOrder.setProductId(userOrders.getProducts()[i]);
            productOrder.setOrderId(orderId);
            this.DAO.addProductOrder(productOrder);
        }

        return Response.ok().build();
    }
}
