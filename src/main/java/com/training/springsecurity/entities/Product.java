package com.training.springsecurity.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
	private int price;
	
	@Column(name = "discounted_price")
	private int discountedPrice;
	
	@Column(name = "discounted_persent")
	private int discountedPersent;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "brand")
	private String Brand;
	
	@Column(name = "material")
	private String material;
	
	@Embedded
	@ElementCollection
	@Column(name = "sizes")
	private Set<Size> sizes = new HashSet<>();
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@OneToMany(mappedBy = "product" , cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Rating> rating  = new ArrayList<>();
	
	@OneToMany(mappedBy = "product" , cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Review> review  = new ArrayList<>();
	
	@Column(name = "num_rating")
	private int numRating;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	public Product(Long id, String title, String description, int price, int discountedPrice, int discountedPersent,
			int quantity, String brand, String material, Set<Size> sizes, String imageUrl, List<Rating> rating,
			List<Review> review, int numRating, Category category) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.discountedPrice = discountedPrice;
		this.discountedPersent = discountedPersent;
		this.quantity = quantity;
		Brand = brand;
		this.material = material;
		this.sizes = sizes;
		this.imageUrl = imageUrl;
		this.rating = rating;
		this.review = review;
		this.numRating = numRating;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(int discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public int getDiscountedPersent() {
		return discountedPersent;
	}

	public void setDiscountedPersent(int discountedPersent) {
		this.discountedPersent = discountedPersent;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String brand) {
		Brand = brand;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Set<Size> getSizes() {
		return sizes;
	}

	public void setSizes(Set<Size> sizes) {
		this.sizes = sizes;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<Rating> getRating() {
		return rating;
	}

	public void setRating(List<Rating> rating) {
		this.rating = rating;
	}

	public List<Review> getReview() {
		return review;
	}

	public void setReview(List<Review> review) {
		this.review = review;
	}

	public int getNumRating() {
		return numRating;
	}

	public void setNumRating(int numRating) {
		this.numRating = numRating;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}




















