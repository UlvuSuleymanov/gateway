package com.tourist.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {
    private final String AUTH_SERVICE_URI="http://37.27.23.25:8081";
    private final String RESTAURANT_SERVICE_URI="http://37.27.23.25:8082";
    private final String CAR_SERVICE_URI="http://37.27.23.25:8083";


    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("restaurant-service", r -> r
                        .path("/restaurant/**",
                                "/filter/**",
                                "/feature/**",
                                "/currency/**",
                                "/cuisine/**",
                                "/comment/**",
                                "/book/**",
                                "/book"
                        )
                        .uri( RESTAURANT_SERVICE_URI))

                .route("auth-service", r -> r
                        .path(
                                "/api/v1/auth/**")
                        .uri(AUTH_SERVICE_URI))
                .route("car-service", r -> r
                        .path(
                                "/api/car/**",
                                "/api/car"
                        )
                        .uri(CAR_SERVICE_URI)
                )
                .build();
    }
}
