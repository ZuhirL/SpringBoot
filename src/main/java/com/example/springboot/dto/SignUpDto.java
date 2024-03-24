package com.example.springboot.dto;

public record SignUpDto(
        String login,
        String password,
        UserRole role) {
}

