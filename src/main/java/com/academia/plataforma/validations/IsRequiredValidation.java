package com.academia.plataforma.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsRequiredValidation implements ConstraintValidator<IsRequired, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) 
	{
		// Verifica si el valor es nulo o vacío
        if (value == null) {
            return false; // No es válido si es nulo
        }

        if (value instanceof String) {
            return !((String) value).trim().isEmpty(); // No es válido si está vacío
        }

        return true; // Es válido para otros tipos de datos
	}

}
