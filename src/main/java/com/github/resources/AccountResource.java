package com.github.resources;

import com.github.modal.Account;
import com.github.security.Secured;
import com.github.service.AccountService;
import com.github.service.InvalidInputException;

import javax.inject.Singleton;
import javax.management.InstanceAlreadyExistsException;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
//@Secured
public class AccountResource extends Resource<AccountService> {

    public AccountResource(AccountService service) {
        super(service);
    }

    @POST
    @Path("/validate")
    public Response validate(Account account) {
        System.out.println(account);
        String JWT = this.service.validate(account);
        if(JWT == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.ok(JWT).build();
    }

    @POST
    @Path("/createAccount")
    public Response createAccount(Account account) {
        try {
            service.createAccount(account);
        } catch (InstanceAlreadyExistsException e) {
            return Response.serverError().entity("Email is already in use").build();
        } catch (InvalidInputException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
        return Response.status(Response.Status.CREATED).build();
    }

}
