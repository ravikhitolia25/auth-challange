package com.auth.security.challange.dto;


import lombok.Builder;

public record LoginDTO(String email, String password) {
    @Builder
    public LoginDTO {}
}
