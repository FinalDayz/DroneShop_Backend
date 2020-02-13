package com.github.resources;

import com.github.modal.Order;
import com.github.modal.UserOrders;
import com.github.security.Secured;
import com.github.service.InvalidInputException;
import com.github.service.OrderService;

import javax.inject.Singleton;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;

@Secured
@Singleton
@Path("/order")
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource extends Resource<OrderService> {
    public OrderResource(OrderService service) {
        super(service);
    }

    @POST
    @Path("/createOrder")
    public Response addOrder(UserOrders order) {
        return service.processOrder(order);
    }
}
