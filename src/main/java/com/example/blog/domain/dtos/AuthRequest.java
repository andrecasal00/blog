package com.example.blog.domain.dtos;

public record AuthRequest(
        String email,
        String password
) {
}
