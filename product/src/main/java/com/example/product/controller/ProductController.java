package com.example.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.exception.ProductNotFoundException;
import com.example.product.model.Product;
import com.example.product.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Operation(summary = "Inserts product details to DB")
	@PostMapping("/product")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Integer> addProduct(@Valid @RequestBody Product product){
		return new ResponseEntity<>(productService.addProduct(product),HttpStatus.CREATED);		
	}
	
	@Operation(summary = "Fetches all products from DB")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/products")
	public ResponseEntity<List<Product>> fetchAllProducts(){
		return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);		
	}
	
	@Operation(summary = "Fetches product of a particular ID from DB")
	@GetMapping("/productdetail/{productId}")
	public ResponseEntity<Product> fetchProductBasedOnId(@PathVariable Integer productId) throws ProductNotFoundException{
		return new ResponseEntity<>(productService.getProductBasedOnId(productId),HttpStatus.OK);		
	}
	
	@Operation(summary = "Fetches products of a particular category from DB")
	@GetMapping("/productdetail")
	public ResponseEntity<List<Product>> fetchProductBasedOnCategory(@RequestParam String category) throws ProductNotFoundException{
		return new ResponseEntity<>(productService.getProductsBasedOnCategory(category),HttpStatus.OK);		
	}
	
	@Operation(summary = "Updates product name of a particular id in DB")
	@PutMapping("/product/{productId}/{productName}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Integer> updateProductDetails(@PathVariable Integer productId, @PathVariable String productName) throws ProductNotFoundException{
		return new ResponseEntity<>(productService.updateProductDetails(productId,productName),HttpStatus.OK);		
	}
	
	@Operation(summary = "Deletes product of a particular ID from DB")
	@DeleteMapping("/product/{productId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Integer> deleteProductDetails(@PathVariable Integer productId) throws ProductNotFoundException{
		return new ResponseEntity<>(productService.deleteProductEntry(productId),HttpStatus.OK);		
	}
	
	

}
