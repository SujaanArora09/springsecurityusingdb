package com.training.springsecurity.security.payload.request;
import java.util.List;
import java.util.Set;

import com.training.springsecurity.entities.Address;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignupRequest {
	@NotBlank
	@Size(min = 3, max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	private String role;

	@NotBlank
	@Size(min = 6, max = 40)
	private String password;
	
	@NotBlank
	@Size(max = 10)
	private String mobile;
	
	 private List<Address> address;
	 
	@NotBlank
	@Size(max = 11)
	private String gender;
	public SignupRequest() {
		// TODO Auto-generated constructor stub
	}
	
	


	public SignupRequest(@NotBlank @Size(min = 3, max = 20) String username,
			@NotBlank @Size(max = 50) @Email String email, String role,
			@NotBlank @Size(min = 6, max = 40) String password, @NotBlank @Size(max = 10) String mobile,
			List<Address> address, @NotBlank @Size(max = 11) String gender) {
		super();
		this.username = username;
		this.email = email;
		this.role = role;
		this.password = password;
		this.mobile = mobile;
		this.address = address;
		this.gender = gender;
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
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public List<Address> getAddress() {
		return address;
	}


	public void setAddress(List<Address> address) {
		this.address = address;
	}


	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
}