package com.baby.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baby.models.Products;

@Repository
public interface ProductRepository extends CrudRepository<Products, Long> {
	
	@Query(value = "Select * from products where added_to_cart_user_userid IS NULL and boughtby_user_userid IS NULL",
			nativeQuery = true)
	List<Products> findAllAvailableProducts();

	Products findByProductId(Long prodid);
	
	@Query(value = "Select * from products where added_to_cart_user_userid = :userID and boughtby_user_userid IS NULL",
			nativeQuery = true)
	List<Products> findProductsInCart(@Param("userID") Long userID);
	
	@Query(value = "Select * from products where boughtby_user_userid = :userID",
			nativeQuery = true)
	List<Products> findProductsBought(@Param("userID") Long userID);


}
