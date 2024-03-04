package com.example.product.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.example.product.model.Product;

import lombok.extern.log4j.Log4j2;

@Component
@Aspect
@Log4j2
public class ProductAscpect {

	@AfterReturning(value = "execution(* com.example.product.service.ProductServiceImpl.addProduct(..)) && args(product)")
	public void addProductAdvice(JoinPoint joinPoint, Product product) {
		log.info("Product successfully inserted to database ",product);
		
	}
	
	@AfterReturning(value = "execution(* com.example.product.service.ProductServiceImpl.getAllProducts())")
	public void getAllProductstAdvice(JoinPoint joinPoint) {
		log.info("All products fetched successfully from database");
		
	}
	
	@AfterReturning(value = "execution(* com.example.product.service.ProductServiceImpl.getProductBasedOnId(..)) && args(productId)")
	public void getProductBasedOnIdAdvice(JoinPoint joinPoint, Integer productId) {
		log.info("Product of particular ID fetched successfully from database",productId);
		
	}
	
	@AfterReturning(value = "execution(* com.example.product.service.ProductServiceImpl.getProductsBasedOnCategory(..)) && args(category)")
	public void getProductsBasedOnCategoryAdvice(JoinPoint joinPoint, String category) {
		log.info("Products from particular category fetched successfully from database",category);
		
	}
	
	@AfterReturning(value = "execution(* com.example.product.service.ProductServiceImpl.updateProductDetails(..)) && args(productId,productName)")
	public void updateProductDetailsAdvice(JoinPoint joinPoint, Integer productId, String productName) {
		log.info("Product name of particular ID updated successfully in database",productId,productName);
		
	}
	
	@AfterReturning(value = "execution(* com.example.product.service.ProductServiceImpl.deleteProductEntry(..)) && args(productId)")
	public void deleteProductEntryAdvice(JoinPoint joinPoint, Integer productId) {
		log.info("Product of particular ID deleted successfully from database",productId);
		
	}
}
