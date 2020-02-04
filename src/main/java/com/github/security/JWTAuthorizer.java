package com.github.security;

import com.github.modal.Account;
import io.dropwizard.auth.Authorizer;

import javax.annotation.Nullable;
import javax.ws.rs.container.ContainerRequestContext;

public class JWTAuthorizer implements Authorizer<Account> {

    /** @deprecated */
    @Override
    public boolean authorize(Account jwtUser, String s) {
        return false;
    }

    @Override
    public boolean authorize(Account principal, String role, @Nullable ContainerRequestContext context) {
        return principal.getAccountRole().equalsIgnoreCase(role);
    }
}
