package com.github.resources;

import com.github.modal.Order;
import com.github.service.InvalidInputException;
import com.github.service.OrderService;

import javax.inject.Singleton;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("/order")
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource extends Resource<OrderService> {
    public OrderResource(OrderService service) {
        super(service);
    }

    @POST
    @Path("/createOrder")
    public Response addOrder(Order order) {
        try {
            service.addOrder(order);
        } catch (InvalidInputException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }

        return Response.status(Response.Status.CREATED).build();
    }
}
