package com.baby.models;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;





@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userID;
	
	private String username;
	
	private String password;
	
	private String role;
	
	@JsonIgnoreProperties("addedToCartUser")
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,mappedBy="addedToCartUser")
	private List<Products> productsInCart;
	
	@JsonIgnoreProperties("boughtbyUser")
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy="boughtbyUser")
	private List<Products> productBought;
	
	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	
	 public List<Products> getProductsInCart() { return productsInCart; }
	 
	 public void setProductsInCart(List<Products> productsInCart) {
	 this.productsInCart = productsInCart; }
	 
	  public List<Products> getProductBought() { return productBought; }
	 
	  public void setProductBought(List<Products> productBought) {
	 this.productBought = productBought; }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User() {
		super();
	}
	
	

}
