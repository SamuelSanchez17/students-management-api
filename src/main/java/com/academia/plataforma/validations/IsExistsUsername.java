package com.academia.plataforma.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = IsExistsUsernameValidation.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsExistsUsername {

	String message() default "The username already exists in the database"; //modificar luego en un archivo de mensajes.properties 
	
	Class<?>[] groups() default {}; 
	
	Class<? extends Payload>[] payload() default {};
	
}
