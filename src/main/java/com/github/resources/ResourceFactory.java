package com.github.resources;

import com.github.persistence.AccountDAO;
import com.github.service.AccountService;
import com.github.service.Service;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import org.skife.jdbi.v2.DBI;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class ResourceFactory {

    private final DBI jdbi;
    private final JerseyEnvironment jersey;

    public ResourceFactory(DBI jdbi, JerseyEnvironment jersey) {
        this.jdbi = jdbi;
        this.jersey = jersey;
    }

    public List<Resource> register(Class<? extends Resource>... resources) {
        ArrayList<Resource> resourceList = new ArrayList<Resource>();
        try {
            for(Class<? extends Resource> resourceClass : resources) {

                Class serviceClass = getGenericClassType(resourceClass);

                Class DAOClass = getGenericClassType(serviceClass);

                Object dao =  jdbi.onDemand(DAOClass);
                Service newService = (Service) serviceClass.getConstructor(DAOClass).newInstance(
                        dao
                );

                Resource newResource = resourceClass.getDeclaredConstructor(serviceClass).newInstance(
                        newService
                );

                resourceList.add(newResource);

                jersey.register(newResource);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resourceList;

    }

    private Class getGenericClassType(Class subClass)
    {
        ParameterizedType parameterizedType = (ParameterizedType) subClass.getGenericSuperclass();
        return (Class) parameterizedType.getActualTypeArguments()[0];
    }
}
