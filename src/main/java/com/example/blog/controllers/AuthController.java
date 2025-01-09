package com.example.blog.controllers;

import com.example.blog.config.security.TokenService;
import com.example.blog.domain.dtos.AuthRequest;
import com.example.blog.domain.dtos.AuthResponse;
import com.example.blog.domain.entities.UserEntity;
import com.example.blog.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
@AllArgsConstructor
public class AuthController {
    private final TokenService tokenService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        UserEntity user = this.userRepository.findByEmail(authRequest.email()).orElseThrow(
                () -> new RuntimeException("User not found!")
        );

        if (authRequest.password().equals(user.getPassword())) {
            AuthResponse authResponse = AuthResponse.builder()
                    .token(tokenService.generateToken(user))
                    .expiresIn(86400)
                    .build();

            return ResponseEntity.ok(authResponse);
        }
        return ResponseEntity.badRequest().build();
    }
}
