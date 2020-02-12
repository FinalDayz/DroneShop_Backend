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
        try {
            this.DAO.updateProduct(product);
        }catch(Exception e) {
            e.printStackTrace();
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
        return Response.ok().build();
    }
}
