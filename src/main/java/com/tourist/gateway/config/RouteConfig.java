package com.tourist.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {
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
                        .uri("http://192.168.15.11:8082"))

                .route("auth-service", r -> r
                        .path(
                                "/api/v1/auth/**")
                        .uri("http://192.168.15.11:8081"))
                .route("car-service", r -> r
                        .path(
                                "/api/car/**",
                                "/api/car"
                        )
                        .uri("http://192.168.15.11:8083")
                )
                .build();
    }
}
