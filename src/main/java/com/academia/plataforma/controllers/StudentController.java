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

import com.academia.plataforma.entities.Student;
import com.academia.plataforma.services.StudentService;
import com.academia.plataforma.utils.ValidationError;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/estudiantes")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@RequestMapping
	public List<Student> list()
	{
		return studentService.findAll();
	}
	
	@RequestMapping("/{id}")
	public ResponseEntity<?> view(@PathVariable("id") Long id)
	{
		System.out.println("Fetching student with ID: " + id);
		
		Optional<Student> studentOptional = studentService.findById(id);
		
		System.out.println("Student found: " + studentOptional.isPresent());
		if(studentOptional.isPresent())
			return ResponseEntity.ok(studentOptional.orElseThrow());
		
		return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping
	public ResponseEntity<?> createStudent(@Valid @RequestBody Student student, BindingResult result)
	{
		if(result.hasFieldErrors())
			return ValidationError.validation(result);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(student));
	}
	
	
	//GET /{id}/notas: Ver notas por estudiante
	@RequestMapping("/{id}/notas")
	public ResponseEntity<?> getNotas(@PathVariable("id") Long id) 
	{
		Optional<Student> studentOptional = studentService.findById(id);

		if (studentOptional.isPresent()) {
			Student student = studentOptional.orElseThrow();
			return ResponseEntity.ok(student.getNotes());
		}

		return ResponseEntity.notFound().build();
	}
	
	
}
