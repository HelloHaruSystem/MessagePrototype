package com.example.MessageModels;

public class AuthRequest implements msg {
    private String username;
    private String token;

    public AuthRequest(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
