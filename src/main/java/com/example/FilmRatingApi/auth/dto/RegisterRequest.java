package com.example.FilmRatingApi.auth.dto;

public class RegisterRequest {

    private String username;
    private String password;

    public RegisterRequest() {}

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
