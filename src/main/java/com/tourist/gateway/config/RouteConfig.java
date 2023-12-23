package com.tourist.gateway.config;

import com.tourist.gateway.properties.RouteList;
import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class RouteConfig {

    private final RouteList ms;

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("restaurant-service", r -> r
                        .path("/api/v1/restaurant/**")
                        .uri(ms.getRestaurantUrl()))
                .route("auth-service", r -> r
                        .path("/api/v1/auth/**")
                        .uri(ms.getAuthUrl()))
                .route("car-service", r -> r
                        .path("/api/v1/car/**")
                        .uri(ms.getCarUrl())
                )
                .build();
    }
}
