package com.training.springsecurity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;

@Entity
public class Review {
	
	@Id
	private long Id;
	
	private String review;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	@JsonIgnore
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Review(long id, String review, Product product, User user) {
		super();
		Id = id;
		this.review = review;
		this.product = product;
		this.user = user;
	}
	
}
