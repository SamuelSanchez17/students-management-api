package com.academia.plataforma.validations;

import org.springframework.beans.factory.annotation.Autowired;

import com.academia.plataforma.repositories.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsExistsUsernameValidation implements ConstraintValidator<IsExistsUsername, String>
{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) 
	{
		if(username == null || username.isEmpty())
		{
			return true; // Si el campo es nulo o vacío, no se valida
		}
		
		return !userRepository.existsByUsername(username); // Retorna true si el username no existe y false si existe el username
	}

}
