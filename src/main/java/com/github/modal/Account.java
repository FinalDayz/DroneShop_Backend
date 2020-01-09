package com.github.modal;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class Account {

    @NotNull
    private int id;

    @NotNull
    @Length(max = 50)
    private String email;

    @Length(max = 50)
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
