package com.training.springsecurity.security.payload.request;

public class JwtResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Long id;
    private String username;
    private String email;
    private String role; // Now a single String

    public JwtResponse() {
        // Default constructor
    }

    public JwtResponse(String accessToken, String tokenType, Long id, String username, String email, String role) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public JwtResponse(String accessToken, Long id, String username, String email, String role) {
        this.accessToken = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    // Getters and Setters
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRoles(String role) {
        this.role = role;
    }
}
