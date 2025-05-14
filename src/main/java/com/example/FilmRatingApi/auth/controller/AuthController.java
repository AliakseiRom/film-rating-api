package com.example.FilmRatingApi.auth.controller;

import com.example.FilmRatingApi.auth.dto.LoginResponse;
import com.example.FilmRatingApi.auth.dto.RegisterRequest;
import com.example.FilmRatingApi.auth.model.User;
import com.example.FilmRatingApi.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        return authService.registerUser(registerRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody User user) {
        return authService.loginUser(user);
    }
}
