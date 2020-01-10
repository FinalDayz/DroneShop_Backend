package com.github.modal;

import io.dropwizard.validation.OneOf;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class Account {

    @NotNull
    private int accountId;

    @NotNull
    @Length(max = 50)
    private String accountEmail;

    @Length(max = 50)
    private String accountPassword;

    @NotNull
    @OneOf(value = {"GEBRUIKER", "ADMIN"})
    private String accountRole;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getAccountRole() {
        return accountRole;
    }

    public void setAccountRole(String accountRole) {
        this.accountRole = accountRole;
    }
}
