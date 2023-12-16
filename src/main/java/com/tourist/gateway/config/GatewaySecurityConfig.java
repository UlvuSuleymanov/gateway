package com.tourist.gateway.config;

import com.tourist.gateway.security.AuthenticationFilter;
import com.tourist.gateway.security.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class GatewaySecurityConfig {

    private static final String[] WHITE_LIST_URL = {
            "/auth/**",
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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(c -> c.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                .requestMatchers(GET, "/cache/clear", "/restaurant/**",
                                        "/filter/**", "/cuisine/**",
                                        "/feature/**", "/book/**",
                                        "/car/**", "/currency/**")
                                .permitAll()
                                // Bu hisse test ucun permitAll dir proda cixdiqdan sonra bu hisse silinecek Start
                                .requestMatchers(POST, "/book").permitAll()
                                // End
                                .anyRequest()
                                .authenticated()
                )
                .addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
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
