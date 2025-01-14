package com.training.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.springsecurity.entities.User;
import com.training.springsecurity.repositories.UserRepository;
import com.training.springsecurity.security.jwt.JwtUtils;
import com.training.springsecurity.security.payload.request.JwtResponse;
import com.training.springsecurity.security.payload.request.LoginRequest;
import com.training.springsecurity.security.payload.request.SignupRequest;
import com.training.springsecurity.security.payload.response.MessageResponse;
import com.training.springsecurity.service.UserImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired 
    private JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), 
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        
        UserImpl userDetails = (UserImpl) authentication.getPrincipal();        
        // Now it's just a String representing the role, not a list
        String role = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .findFirst()  // Each user has only one role
                .orElse("USER"); // Default to "ROLE_USER" if no role found

        return ResponseEntity.ok(new JwtResponse(jwt, 
                                                 userDetails.getId(), 
                                                 userDetails.getUsername(), 
                                                 userDetails.getEmail(), 
                                                 role));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        // Check if the username already exists
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        
        // Check if the email already exists
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        // Set role (if provided in the request)
        user.setRole(getRole(signUpRequest));

        // Set the addresses and other properties
        if (signUpRequest.getAddress() != null) {
            user.setAddress(signUpRequest.getAddress());  // Set addresses directly
        }

        user.setMobile(signUpRequest.getMobile());
        user.setGender(signUpRequest.getGender());

        // Saving User to the database
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    private String getRole(SignupRequest signupRequest) {
        // If no role is specified, default to "ROLE_USER"
        if (signupRequest.getRole() == null || signupRequest.getRole().isEmpty()) {
            return "USER";
        }
        // Assuming only one role is provided per user
        return signupRequest.getRole();
    }
}
