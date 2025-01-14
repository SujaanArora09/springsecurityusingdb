package com.training.springsecurity.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.training.springsecurity.entities.User;

import java.util.Collection;
import java.util.Collections;

public class UserImpl implements UserDetails {

    private Long id;
    private String username;
    private String email;
    private String password;
    private String role; 

    public UserImpl(Long id, String username, String email, String password, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // This method should be used to return roles as a list of authorities
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static UserImpl build(User user) {
        String role = user.getRole() != null ? user.getRole() : "USER";  
        return new UserImpl(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getPassword(),
            role
        );
    }
}
