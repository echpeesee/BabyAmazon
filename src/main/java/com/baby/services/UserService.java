package com.baby.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.baby.models.User;
import com.baby.repository.UserRepository;

public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public User findUserbyUsername(String username) {
		return userRepository.findByUsername(username);
		
	}

}
