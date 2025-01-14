package com.training.springsecurity.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user_details", uniqueConstraints = { @UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") })
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	
	@NotBlank
	@Size(max = 120)
	private String Role;

	@NotBlank
	@Size(max = 120)
	private String password;

	@NotBlank
	@Size(max = 10)
	private String mobile;

	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
	@Size(max = 220)
	private List<Address> address = new ArrayList<>();
	
	@Embedded
	@ElementCollection
	@CollectionTable(name = "payment_information" , joinColumns = @JoinColumn(name = "user-id"))
	private List<PaymentInformation> payInformation = new ArrayList<>();
	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Rating> ratings = new ArrayList<>();
	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Review> reviews = new ArrayList<>();
	
	@NotBlank
	@Size(max = 11)
	private String gender;

	
	public User() {
		// TODO Auto-generated constructor stub
	}



	public User(Long id, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(max = 120) String role, @NotBlank @Size(max = 120) String password,
			@NotBlank @Size(max = 10) String mobile, @Size(max = 220) List<Address> address,
			List<PaymentInformation> payInformation, List<Rating> ratings, List<Review> reviews,
			@NotBlank @Size(max = 11) String gender) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		Role = role;
		this.password = password;
		this.mobile = mobile;
		this.address = address;
		this.payInformation = payInformation;
		this.ratings = ratings;
		this.reviews = reviews;
		this.gender = gender;
	}



	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
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
	
	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

}