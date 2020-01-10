package com.github.service;

public abstract class Service<DAOType> {

    protected final DAOType DAO;

    public Service(DAOType DAO) {
        this.DAO = DAO;
    }
}
