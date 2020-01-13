package com.github.resources;

import com.github.modal.ProductOrder;
import com.github.service.InvalidInputException;
import com.github.service.ProductOrderService;

import javax.inject.Singleton;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("/productOrder")
@Produces(MediaType.APPLICATION_JSON)
public class ProductOrderResource extends Resource<ProductOrderService> {
    public ProductOrderResource(ProductOrderService service) {
        super(service);
    }

    @POST
    @Path("/createOrder")
    public Response addProductOrder(ProductOrder productOrder) {
        try {
            service.addProductOrder(productOrder);
        } catch (InvalidInputException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }

        return Response.status(Response.Status.CREATED).build();
    }
}
