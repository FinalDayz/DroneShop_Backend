package com.github;

import com.github.resources.AccountResource;
import com.github.resources.ProductResource;
import com.github.resources.ResourceFactory;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import org.skife.jdbi.v2.DBI;

import java.util.EnumSet;

public class DroneShop_BackendApplication extends Application<DroneShop_BackendConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DroneShop_BackendApplication().run(args);
    }

    @Override
    public String getName() {
        return "DroneShop_Backend";
    }

    @Override
    public void initialize(final Bootstrap<DroneShop_BackendConfiguration> bootstrap) {

    }

    @Override
    public void run(final DroneShop_BackendConfiguration configuration,
                    final Environment environment) {
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");


        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        registerInjections(configuration, environment);
        environment.jersey().packages("org.example.resource");
    }

    private void registerInjections(DroneShop_BackendConfiguration config, Environment env) {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(env, config.getDataSourceFactory(), "postgresql");

        ResourceFactory resourceFactory = new ResourceFactory(jdbi, env.jersey());
        resourceFactory.register(AccountResource.class);
        resourceFactory.register(ProductResource.class);

//        env.jersey().register(new AccountResource());
    }
}
