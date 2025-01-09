package com.example.blog.domain.dtos;

import lombok.Builder;

@Builder
public record AuthResponse(
        String token,
        long expiresIn
) {
}
