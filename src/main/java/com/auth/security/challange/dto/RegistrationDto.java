package com.auth.security.challange.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

@Data
@Getter
@Setter
public class RegistrationDto {

    private String username;
    private String email;
    private String password;
}
