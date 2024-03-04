package com.example.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.product.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{
	
	List<ProductEntity> findByCategory(String category);
	
	@Query("update ProductEntity p set p.productName = ?2 where p.productID = ?1")
	@Modifying
	void updateProductName(Integer productID, String productName);

}
