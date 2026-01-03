package com.academia.plataforma.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academia.plataforma.entities.AcademicTerm;
import com.academia.plataforma.services.AcademicTermService;
import com.academia.plataforma.utils.ValidationError;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/periodos")
public class AcademicTermController {
	
	@Autowired
	private AcademicTermService academicTermService;
	
	@PostMapping
	public ResponseEntity<?> createPeriod(@Valid @RequestBody AcademicTerm academicTerm, BindingResult result)
	{
		if(result.hasFieldErrors())
			return ResponseEntity.badRequest().body(ValidationError.validation(result));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(academicTermService.save(academicTerm));
	}
	
	
	@RequestMapping
	public List<AcademicTerm> list()
	{
		return academicTermService.findAll();
	}
	
	
	@RequestMapping("/{id}")
	public ResponseEntity<?> view(@PathVariable("id") Long id)
	{
		Optional<AcademicTerm> academicTermOptional = academicTermService.findById(id);
		
		if(academicTermOptional.isPresent())
			return ResponseEntity.ok(academicTermOptional.orElseThrow());
		
		return ResponseEntity.notFound().build();
	}

}
