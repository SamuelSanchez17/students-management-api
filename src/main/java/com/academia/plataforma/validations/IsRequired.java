package com.academia.plataforma.validations;

import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = IsRequiredValidation.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsRequired {
	
	String message() default "{El campo ... esta nulo}"; //modificar luego en un archivo de mensajes.properties 
	
	Class<?>[] groups() default {}; 
	
	Class<? extends Payload>[] payload() default {};

}
