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

import com.academia.plataforma.entities.Course;
import com.academia.plataforma.services.CourseService;
import com.academia.plataforma.utils.ValidationError;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cursos")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createCourse(@Valid @RequestBody Course course, BindingResult result)
	{
		if(result.hasFieldErrors())
			return ValidationError.validation(result);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(course));
	}
	
	
	@RequestMapping
	public List<Course> list()
	{
		return courseService.findAll();
	}

	
	@RequestMapping("/{id}")
	public ResponseEntity<?> view(@PathVariable("id") Long id)
	{
		Optional<Course> courseOptional = courseService.findById(id);
		
		if(courseOptional.isPresent())
			return ResponseEntity.ok(courseOptional.orElseThrow());
		
		return ResponseEntity.notFound().build();
	}
	
}
