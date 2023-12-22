package com.tourist.gateway.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.http.HttpMethod.GET;


@Configuration
@EnableWebFluxSecurity
@EnableWebSecurity
public class GatewaySecurityConfig {

    private static final String[] WHITE_LIST_URL = {

            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"};

    private static final String[] allowedGetApis = {
            "/cache/clear",
            "/restaurant/**",
            "/filter/**",
            "/cuisine/**",
            "/feature/**",
            "/book/**",
            "/api/car/**",
            "/currency/**"
    };
    private static final String[] allowedPostApis = {
            "/api/car/book"
    };


    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .cors(c -> c.configurationSource(corsConfigurationSource()))
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(GET, WHITE_LIST_URL).permitAll()
                        .pathMatchers(GET, allowedGetApis).permitAll()
                        .anyExchange().authenticated()
                )
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable);
        return http.build();
    }


    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Todo: Bu hisse proda cixdiqdan sonra Mobile ve Admin panel ucun Url yazilacaq ki ancaq onlardan icaze olsun.
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration
                .setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "DELETE", "OPTIONS", "PATCH"));
        configuration.setMaxAge(3600L);
        configuration.setAllowedHeaders(Arrays
                .asList("Authorization", "x-xsrf-token", "Accept-language", "Access-Control-Allow-Headers",
                        "Origin", "Accept", "X-Requested-With",
                        "Content-Type", "Access-Control-Request-Method", "Access-Control-Request-Headers",
                        "Access-Control-Expose-Headers", "X-Session-Id", "X-Platform"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
