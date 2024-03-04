package com.example.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.product.entity.ProductEntity;
import com.example.product.exception.ProductNotFoundException;
import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public Integer addProduct(Product product) {
		ProductEntity productEntity = new ProductEntity();
			productEntity = ProductEntity.builder().productID(product.getProductID())
					.productName(product.getProductName()).price(product.getPrice()).category(product.getCategory())
					.build();
			productRepository.save(productEntity);

		return productEntity.getProductID();
	}

	@Override
	public List<Product> getAllProducts() {
		List<ProductEntity> productEntity = productRepository.findAll();
		List<Product> productList = new ArrayList<>();
		productEntity.forEach(p -> {
			Product product = Product.builder().productID(p.getProductID()).productName(p.getProductName())
					.price(p.getPrice()).category(p.getCategory()).build();
			productList.add(product);
		});
		return productList;
	}

	@Override
	public Product getProductBasedOnId(Integer productId) throws ProductNotFoundException {
		ProductEntity productEntity = checkProductID(productId);

		Product product = Product.builder().productID(productEntity.getProductID())
				.productName(productEntity.getProductName()).price(productEntity.getPrice())
				.category(productEntity.getCategory()).build();
		return product;
	}

	@Override
	public List<Product> getProductsBasedOnCategory(String category) throws ProductNotFoundException {
		List<ProductEntity> productEntityList = productRepository.findByCategory(category);
		List<Product> productList = new ArrayList<>();
		if (!productEntityList.isEmpty())
			productEntityList.forEach(p -> {
				Product product = Product.builder().productID(p.getProductID()).productName(p.getProductName())
						.price(p.getPrice()).category(p.getCategory()).build();
				productList.add(product);
			});
		else
			throw new ProductNotFoundException("Product not found for the given category: " + category);
		return productList;
	}

	@Override
	public Integer updateProductDetails(Integer productId, String productName) throws ProductNotFoundException {
		if (checkProductID(productId)  != null)
			productRepository.updateProductName(productId, productName);

		return productId;
	}

	@Override
	public Integer deleteProductEntry(Integer productId) throws ProductNotFoundException {
		if (checkProductID(productId) != null)
			productRepository.deleteById(productId);
		
		return productId;
	}

	public ProductEntity checkProductID(Integer productId) throws ProductNotFoundException {
		ProductEntity productEntity = productRepository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("Product not found for the given ID: " + productId));
		return productEntity;

	}

}
