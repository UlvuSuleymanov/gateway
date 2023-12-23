package com.tourist.gateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "services.url")
public class RouteList {

    private String authUrl;
    private String carUrl;
    private String restaurantUrl;
}
