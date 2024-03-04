package com.example.product.service;

import java.util.List;

import com.example.product.exception.ProductNotFoundException;
import com.example.product.model.Product;

public interface ProductService {
	
	Integer addProduct(Product product);
	
	List<Product> getAllProducts();
	
	Product getProductBasedOnId(Integer productId) throws ProductNotFoundException;
	
	List<Product> getProductsBasedOnCategory(String category) throws ProductNotFoundException;
	
	Integer updateProductDetails(Integer productId, String productName) throws ProductNotFoundException;
	
	Integer deleteProductEntry(Integer productId) throws ProductNotFoundException;

}
