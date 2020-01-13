package com.github.service;

import com.github.modal.Order;
import com.github.persistence.OrderDAO;

public class OrderService extends Service<OrderDAO> {
    public OrderService(OrderDAO DAO) {
        super(DAO);
    }

    public void addOrder(Order order) throws InvalidInputException{
        //TODO: add checks (ex. accountId)
        DAO.addOrder(order);
    }
}
