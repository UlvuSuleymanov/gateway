package com.tourist.gateway.security;

import com.tourist.gateway.dto.AuthorizeResponseDto;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AuthenticationService {
    public AuthorizeResponseDto validateAccess(String token) {
        return WebClient.create()
                .post()
                .uri("http://192.168.15.11:8081",
                        uriBuilder -> uriBuilder.queryParam("token", token).build())
                .retrieve()
                .bodyToMono(AuthorizeResponseDto.class)
                .block();
    }
}
