package com.academia.plataforma.validations;

import com.academia.plataforma.entities.AcademicTerm;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateRangeValidation implements ConstraintValidator<ValidDateRange, AcademicTerm>
{

	@Override
	public boolean isValid(AcademicTerm academicTerm, ConstraintValidatorContext context) 
	{
		 // Asume que @NotNull ya valida que las fechas no sean nulas
        return !academicTerm.getStartDate().after(academicTerm.getEndDate());
		
	}

}
