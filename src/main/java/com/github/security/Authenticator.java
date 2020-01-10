package com.github.security;

import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.basic.BasicCredentials;
import com.github.modal.Account;

public class Authenticator implements io.dropwizard.auth.Authenticator<BasicCredentials, Account> {
    @Override
    public Optional<Account> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {

        return null;
    }
}
