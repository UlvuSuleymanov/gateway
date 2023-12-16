package com.tourist.gateway.dto;

 import com.tourist.gateway.enums.Role;
 import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthUserDto {

    Integer id;
    String firstname;
    String lastname;
    String email;
    String password;
    Role role;

}

