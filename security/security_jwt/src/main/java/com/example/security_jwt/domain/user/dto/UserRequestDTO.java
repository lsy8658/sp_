package com.example.security_jwt.domain.user.dto;

public record UserRequestDTO (
        String username,
        String password
) {

}