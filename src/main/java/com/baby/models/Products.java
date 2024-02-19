package com.baby.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Products {
	
	@Id
	private Long productId;
	
	private String productName;
	
	@JsonIgnoreProperties("productBought")
	@ManyToOne
	private User boughtbyUser;
	
	@JsonIgnoreProperties("productsInCart")
	@ManyToOne
	private User addedToCartUser;
	
	private float price;
	
	private String brand;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public User getBoughtbyUser() {
		return boughtbyUser;
	}

	public void setBoughtbyUser(User boughtbyUser) {
		this.boughtbyUser = boughtbyUser;
	}

	public User getAddedToCartUser() {
		return addedToCartUser;
	}

	public void setAddedToCartUser(User addedToCartUser) {
		this.addedToCartUser = addedToCartUser;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	

}
