package com.academia.plataforma.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.academia.plataforma.entities.Subject;
import com.academia.plataforma.services.SubjectService;
import com.academia.plataforma.utils.ValidationError;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/asignaturas")
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	
	@RequestMapping
	public List<Subject> list()
	{
		return subjectService.findAll();
	}
	
	@RequestMapping("/{id}")
	public ResponseEntity<?> view(@PathVariable("id") Long id)
	{
		Optional<Subject> subjectOptional = subjectService.findById(id);
		
		if(subjectOptional.isPresent())
			return ResponseEntity.ok(subjectOptional.orElseThrow());
		
		return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping
	public ResponseEntity<?> createSubject(@Valid @RequestBody Subject subject, BindingResult result)
	{
		if(result.hasFieldErrors())
			return ValidationError.validation(result);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.save(subject));
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @PathVariable("id") Long id, @RequestBody Subject subject, BindingResult result)
	{
		
		if(result.hasFieldErrors())
			return ValidationError.validation(result);
		
		System.out.println("Updating Subject with ID: " + id);
		
		Optional<Subject> subjectOptional = subjectService.update(id, subject);
		
		if(subjectOptional.isPresent())
			return ResponseEntity.status(HttpStatus.CREATED).body(subjectOptional.get() ); //el get retorna el objeto actualizado, y el orElseThrow lanza una excepción si no se encuentra el producto
	
		return ResponseEntity.notFound().build(); //si no se encuentra el producto, retorna un error 404
		
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id)
	{
		Optional<Subject> subjectOptional = subjectService.delete(id);
		
		if (subjectOptional.isPresent()) 
			return ResponseEntity.ok(subjectOptional.get() ); // retorna un 204 No Content
		
		return ResponseEntity.noContent().build();
	}
	
	

}
