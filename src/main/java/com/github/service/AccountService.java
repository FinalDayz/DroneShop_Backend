package com.github.service;

import com.github.persistence.UserDAO;

public class AccountService {
    private final UserDAO userDAO;

    public AccountService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
