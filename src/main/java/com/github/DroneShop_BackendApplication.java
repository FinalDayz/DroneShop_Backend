package com.github;

import com.github.modal.Account;
import com.github.resources.*;
import com.github.security.AuthFilter;
import com.github.security.JWTAuthenticator;
import com.github.security.JWTAuthorizer;
import com.github.service.JsonWebTokenService;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
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
        cors.setInitParameter("allowedHeaders", "*");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.setInitParameter("Access-Control-Allow-Origin", "*");
        cors.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");

        // register authentication/authorization
        environment.jersey().register(new AuthDynamicFeature(
                new OAuthCredentialAuthFilter.Builder<Account>()
                        .setAuthenticator(new JWTAuthenticator(new JsonWebTokenService()))
                        .setAuthorizer(new JWTAuthorizer())
                        .setPrefix("Bearer")
                        .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);

        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        registerInjections(configuration, environment);
        environment.jersey().packages("org.example.resource");
    }

    private void registerInjections(DroneShop_BackendConfiguration config, Environment env) {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(env, config.getDataSourceFactory(), "mysql");

        ResourceFactory resourceFactory = new ResourceFactory(jdbi, env.jersey());
        resourceFactory.register(AccountResource.class);
        resourceFactory.register(ProductResource.class);
        resourceFactory.register(ProductOrderResource.class);
        resourceFactory.register(OrderResource.class);

        env.jersey().register(new AuthFilter(config));

    }
}
