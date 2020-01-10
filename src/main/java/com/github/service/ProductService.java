package com.github.service;

import com.github.modal.Product;
import com.github.persistence.ProductDAO;

import java.util.List;

public class ProductService extends Service<ProductDAO> {
    public ProductService(ProductDAO DAO) {
        super(DAO);
    }

    public List<Product> getAllProducts() {
        return DAO.selectAllProducts();
    }
}
