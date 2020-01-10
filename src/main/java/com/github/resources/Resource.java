package com.github.resources;

import com.github.service.Service;

public abstract class Resource<serviceType> {

    protected final serviceType service;

    public Resource(serviceType service) {
        this.service = service;

    }
}
