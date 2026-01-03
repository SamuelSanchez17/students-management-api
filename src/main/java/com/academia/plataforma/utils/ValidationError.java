package com.academia.plataforma.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class ValidationError {

	//metodo que se encarga de las validaciones
    //se necesita cambiar a public y agregar static al metodo para poder usarlo en otros controladores sin necesidad de instanciar la clase ValidationError
	public static ResponseEntity<?> validation(BindingResult result) 
	{
		//mete los errores de validación en un mapa, para luego retornarlo como respuesta
		Map<String, String> errors = new HashMap<>();
		result.getFieldErrors().forEach(
				error -> errors.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage())
				);
			
		return ResponseEntity.badRequest().body(errors); //retorna el mapa de errores con un status 400 Bad Request
	}
	
}
