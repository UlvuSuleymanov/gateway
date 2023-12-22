package com.tourist.gateway.dto;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPrincipal {
    private final String transactionNumber = UUID.randomUUID().toString();
    private String token;
}
