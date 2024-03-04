package com.example.product.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.product.util.ProductCategoryValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy  = ProductCategoryValidator.class)
public @interface ValidCategory {
	
	public String message() default "Invalid product category. It should be Electronics/Stationary/Food/Utensils/Fashion";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
