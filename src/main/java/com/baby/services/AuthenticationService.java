package com.baby.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.baby.models.User;
import com.baby.repository.UserRepository;
import com.baby.security.JwtService;

@Service
public class AuthenticationService {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	UserRepository userRepo;
	
	public User authenticate(User user, HttpHeaders responseHeader) {
		String token = "";
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		User userBody = userRepo.findByUsername(user.getUsername());
		if (authentication.isAuthenticated()) {
			token = jwtService.generateToken(user.getUsername());
		}
		responseHeader.set("token", token);
		return userBody;
	}

}
