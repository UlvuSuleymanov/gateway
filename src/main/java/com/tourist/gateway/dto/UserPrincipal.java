package com.tourist.gateway.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserPrincipal {
    private final String transactionNumber = UUID.randomUUID().toString();
    private String token;
}
