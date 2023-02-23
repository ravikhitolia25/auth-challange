package com.auth.security.challange.dto;

import lombok.Builder;

public record LoginResponseDto(String username, String password, String email) {

    @Builder
    public LoginResponseDto{};
}
