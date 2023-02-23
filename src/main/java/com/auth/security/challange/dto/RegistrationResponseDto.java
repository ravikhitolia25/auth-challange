package com.auth.security.challange.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegistrationResponseDto {

    private String username;
    private String email;
}
