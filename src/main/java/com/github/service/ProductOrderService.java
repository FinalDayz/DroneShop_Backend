package com.github.service;

import com.github.modal.ProductOrder;
import com.github.persistence.ProductOrderDAO;

public class ProductOrderService extends Service<ProductOrderDAO> {
    public ProductOrderService(ProductOrderDAO DAO) {
        super(DAO);
    }

    public void addProductOrder(ProductOrder productOrder) throws InvalidInputException{
        //TODO: add checks (ex. order not found, product not found)
        DAO.addProductOrder(productOrder);
    }
}
