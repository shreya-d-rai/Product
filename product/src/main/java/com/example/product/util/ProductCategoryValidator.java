package com.example.product.util;

import java.util.Arrays;
import java.util.List;

import com.example.product.annotation.ValidCategory;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProductCategoryValidator implements ConstraintValidator<ValidCategory, String>{

	@Override
	public boolean isValid(String category, ConstraintValidatorContext context) {
		List<String> productCategories = Arrays.asList("Electronics","Stationary","Food","Utensils","Fashion");
		return productCategories.contains(category);
	}

	
}
