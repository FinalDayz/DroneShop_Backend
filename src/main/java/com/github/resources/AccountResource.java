package com.github.resources;

import com.github.modal.Account;
import com.github.security.Secured;
import com.github.service.AccountService;
import com.github.service.InvalidInputException;
import org.skife.jdbi.v2.sqlobject.BindBean;

import javax.inject.Singleton;
import javax.management.InstanceAlreadyExistsException;
import javax.validation.Valid;
import javax.ws.rs.*;
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
        Account returnedAccount = this.service.validate(account);
        if(returnedAccount == null) {
            System.out.println("Incorrect login");
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.ok(returnedAccount).build();
    }

    @GET
    @Path("/validateJWT/{JWT}")
    public Response validateJWT(@PathParam("JWT") String JWT) {
        Account returnedAccount = this.service.validateJWT(JWT);
        if(returnedAccount == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.ok(returnedAccount).build();
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
