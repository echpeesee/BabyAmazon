package com.baby.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baby.models.Products;
import com.baby.models.User;
import com.baby.services.ProductsService;
import com.baby.services.AuthenticationService;
import com.baby.security.JwtService;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
public class Controller {
	
	@Autowired
	ProductsService productsService;
	
	@Autowired
	AuthenticationService authService;
	
	
	
	//login user  or admin
	
	@PostMapping("/login")	
	public ResponseEntity<User> login (@RequestBody User user) throws Exception {
		 HttpHeaders responseHeaders = new HttpHeaders();
		 User userBody = authService.authenticate(user, responseHeaders);
		 return ResponseEntity.ok().headers(responseHeaders).body(userBody);
	}
	// get all products 
	@GetMapping("/allProducts")
	public ResponseEntity<List<Products>> getAllProducts() {
		List<Products> products = productsService.getAllProducts();
		return new ResponseEntity <List<Products>> (products, HttpStatus.OK);
	}
	
	//dummy endpoint for experiments
	@GetMapping("/check")
	public ResponseEntity<String> testing () {
		return new ResponseEntity<String>("checked", HttpStatus.OK);
	}
	
	// add to cart 
	@PostMapping("/addToCart/{productID}")
	public ResponseEntity<Products> addToCart(@RequestBody User user, @PathVariable("productID") Long productID) {
		Products product = productsService.addToCart(productID,user );
		return  new ResponseEntity<Products>(product, HttpStatus.OK);
		
	}
	// buy
	@PostMapping("/buyProduct/{productID}")
	public ResponseEntity<Products> buyProduct(@RequestBody User user, @PathVariable("productID") Long productID) {
		Products product = productsService.buyProduct(productID,user );
		return  new ResponseEntity<Products>(product, HttpStatus.OK);
	}
	
	//get products in cart after a transaction of add to cart and route.
	@GetMapping("/productsInCart/{username}")
	public ResponseEntity<List<Products>> getProducts(@PathVariable("username") String username) {
		List<Products> products = productsService.getproductsInCart(username);
		return  new ResponseEntity <List<Products>> (products, HttpStatus.OK);
	}
	
	// get products ordered affter a transacaton of order and route
	@GetMapping("/productsOrdered/{username}")
	public ResponseEntity<List<Products>> productsOrdered(@PathVariable("username") String username) {
		List<Products> products = productsService.getProductsOrdered(username);
		return  new ResponseEntity <List<Products>> (products, HttpStatus.OK);
	}
	
	//admin report api
	
}
