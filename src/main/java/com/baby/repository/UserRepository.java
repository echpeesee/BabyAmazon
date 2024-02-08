package com.baby.repository;

import org.springframework.data.repository.CrudRepository;

import com.baby.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);

}
