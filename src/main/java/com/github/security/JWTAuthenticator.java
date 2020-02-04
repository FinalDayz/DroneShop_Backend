package com.github.security;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.modal.Account;
import com.github.service.JsonWebTokenService;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

import javax.inject.Inject;
import java.util.Optional;

public class JWTAuthenticator implements Authenticator<String, Account> {
    private JsonWebTokenService service;

    @Inject
    public JWTAuthenticator(JsonWebTokenService service) {
        this.service = service;
    }

    @Override
    public Optional<Account> authenticate(String jwtToken) throws AuthenticationException {
        Account user = null;
        if (tokenIsValid(jwtToken)) {

            DecodedJWT decodedJWT = service.decodeJwt(jwtToken);
            Claim role = decodedJWT.getClaim("role");
            Claim name = decodedJWT.getClaim("name");

            if (role.isNull() || name.isNull()) {
                return failAuth();
            }

            user = new Account();
            user.setAccountRole(role.asString());
            user.setAccountEmail(name.asString());

            return Optional.of(user);
        }

        return failAuth();
    }

    private boolean tokenIsValid(String token) {
        return service.isValid(token);
    }

    private Optional<Account> failAuth() {
        return Optional.empty();
    }
}