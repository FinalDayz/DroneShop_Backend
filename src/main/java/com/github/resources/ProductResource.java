package com.github.resources;

import com.github.modal.Product;
import com.github.security.Secured;
import com.github.service.ProductService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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

    @POST
    @Path("/edit")
    @RolesAllowed({"Admin"})
    public Response updateProduct(Product product) {
        return this.service.updateProduct(product);
    }

    @PUT
    @Path("/create")
    @RolesAllowed({"Admin"})
    public Response createProduct(Product product) {
        return this.service.createProduct(product);
    }

    @DELETE
    @RolesAllowed({"Admin"})
    @Path("delete/{productId}")
    public Response deleteProduct(@PathParam("productId") int productId) {
        return this.service.deleteProduct(productId);
    }
}
