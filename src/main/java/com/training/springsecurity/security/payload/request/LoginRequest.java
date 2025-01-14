package com.training.springsecurity.security.payload.request;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank
	private String email;

	@NotBlank
	private String password;

	public LoginRequest() {
		// TODO Auto-generated constructor stub
	}

	public LoginRequest(@NotBlank String email, @NotBlank String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}