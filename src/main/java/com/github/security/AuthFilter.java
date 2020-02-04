package com.github.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.github.DroneShop_BackendApplication;
import com.github.DroneShop_BackendConfiguration;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Secured
@Provider
@Priority(value = Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {

    public static final String AUTHENTICATION_SCHEME = "Bearer ";
    private Algorithm algorithm;
    private JWTVerifier verifier;

    public AuthFilter(DroneShop_BackendConfiguration config) {
        this.algorithm = Algorithm.HMAC256(JWTUtils.SECRET);
        this.verifier = JWT.require(algorithm).withIssuer(JWTUtils.ISSUER).build();
    }

    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        if (isPreflightRequest(context)) {
            success(context);
            return;
        }
        String authorizationHeader = context.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (!isValidHeader(authorizationHeader)) {
            abortUnauthorized(context);
            return;
        }

        String jwtToken = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

        try {
            verifier.verify(jwtToken);
        } catch (JWTVerificationException exception) {
            abortUnauthorized(context);
        }
    }

    private void abortUnauthorized(ContainerRequestContext context) {
        this.abortByRequest(context, Response.status(Response.Status.UNAUTHORIZED).build());
    }

    private void success(ContainerRequestContext context) {
        this.abortByRequest(context, Response.ok().build());
    }

    private void abortByRequest(ContainerRequestContext context, Response response) {
        context.abortWith(response);
    }


    private static boolean isPreflightRequest(ContainerRequestContext request) {
        return request.getHeaderString("Origin") != null
                && request.getMethod().equalsIgnoreCase("OPTIONS");
    }

    private boolean isValidHeader(String authorizationHeader) {
        return authorizationHeader != null && authorizationHeader
                .toLowerCase().startsWith(AUTHENTICATION_SCHEME.toLowerCase());
    }
}
