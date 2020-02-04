package com.github.resources;

import com.github.modal.Product;
import com.github.security.Secured;
import com.github.service.ProductService;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Secured
@Singleton
@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource extends Resource<ProductService> {
    public ProductResource(ProductService service) {
        super(service);
    }

    @GET
    @Path("/all")
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }
}
