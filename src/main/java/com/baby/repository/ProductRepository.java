package com.baby.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.baby.models.Products;

@Repository
public interface ProductRepository extends CrudRepository<Products, Long> {
	
	@Query(value = "Select * from products where added_to_cart_user_userid IS NULL and boughtby_user_userid IS NULL",
			nativeQuery = true)
	List<Products> findAllAvailableProducts();

	Products findByProductId(Long prodid);


}
