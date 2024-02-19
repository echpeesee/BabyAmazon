package com.baby.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baby.models.Products;
import com.baby.models.User;
import com.baby.repository.ProductRepository;
import com.baby.repository.UserRepository;

@Service
public class ProductsService {
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	UserRepository userRepository;
	
	
	 public List<Products> getAllProducts() {
		return productRepo.findAllAvailableProducts();	
	}


	public Products addToCart(Long productID, User user) {
		Products prod = productRepo.findByProductId(productID);
		User userInstance = userRepository.findByUsername(user.getUsername());
		prod.setAddedToCartUser(userInstance);
		return productRepo.save(prod);
		
	}
	
	public Products buyProduct(Long productID, User user) {
		Products prod = productRepo.findByProductId(productID);
		User userInstance = userRepository.findByUsername(user.getUsername());
		prod.setBoughtbyUser(userInstance);
		return productRepo.save(prod);
	
	}

	public List<Products> getproductsInCart(String username) {
		User user = userRepository.findByUsername(username);
		return productRepo.findProductsInCart(user.getUserID());
	}
	
	public List<Products> getProductsOrdered(String username) {
		User user = userRepository.findByUsername(username);
		return productRepo.findProductsBought(user.getUserID());
	}
	
}
