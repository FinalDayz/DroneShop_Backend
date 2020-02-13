package com.github.service;

import com.github.modal.Product;
import com.github.persistence.ProductDAO;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

public class ProductService extends Service<ProductDAO> {
    public ProductService(ProductDAO DAO) {
        super(DAO);
    }

    public List<Product> getAllProducts() {
        return DAO.selectAllProducts();
    }

    public Response updateProduct(Product product) {
        this.DAO.updateProduct(product);
        return Response.ok().build();
    }

    public Response createProduct(Product product) {
        this.DAO.insertProduct(product);
        return Response.ok().build();
    }

    public Response deleteProduct(int productId) {
        this.DAO.deleteProduct(productId);
        return Response.ok().build();
    }
}
