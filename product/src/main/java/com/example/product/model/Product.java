package com.example.product.model;

import com.example.product.annotation.ValidCategory;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

	Integer productID;

	@NotEmpty(message = "Product name must not be empty")
	@NotNull(message = "Product name must not be null")
	String productName;

	Integer price;

	@ValidCategory
	String category;

}
