package com.academia.plataforma.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = DateRangeValidation.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateRange {

	String message() default "La fecha de inicio debe ser anterior a la fecha de término";
	
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
