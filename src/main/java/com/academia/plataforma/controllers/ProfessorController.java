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

import com.academia.plataforma.entities.Professor;
import com.academia.plataforma.entities.Subject;
import com.academia.plataforma.services.ProfessorService;
import com.academia.plataforma.utils.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/profesores")
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@RequestMapping
	public List<Professor> list()
	{
		return professorService.findAll();
	}
	
	
	@RequestMapping("/{id}")
	public ResponseEntity<?> view(@PathVariable("id") Long id)
	{
		Optional<Professor> professorOptional = professorService.findById(id);
		
		if(professorOptional.isPresent())
			return ResponseEntity.ok(professorOptional.orElseThrow());
		
		return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping
	public ResponseEntity<?> createProfessor(@Valid @RequestBody Professor professor, BindingResult result)
	{
		if(result.hasFieldErrors())
			return ValidationError.validation(result);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(professorService.save(professor));
	}
	
	//GET /{id}/asignaturas: Ver asignaturas asignadas
	@RequestMapping("/{id}/asignaturas")
	public ResponseEntity<?> getAsignaturas(@PathVariable("id") Long id) 
	{
		Optional<Professor> professorOptional = professorService.findById(id);

		if (professorOptional.isPresent()) {
			List<Subject> asignaturas = professorService.findSubjectsByProfessorId(id);
			return ResponseEntity.ok(asignaturas);
		}

		return ResponseEntity.notFound().build();
	}

}
