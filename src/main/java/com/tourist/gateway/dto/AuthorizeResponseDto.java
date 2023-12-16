package com.tourist.gateway.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthorizeResponseDto {

    private AuthUserDto user;
    private Boolean isAuthorize;

}
