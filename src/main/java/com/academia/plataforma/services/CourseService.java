package com.academia.plataforma.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.academia.plataforma.entities.Course;

@Service
public interface CourseService {

	//GET /: Listar cursos
	List<Course> findAll();
	
	//GET /{id}: Ver detalles
	Optional<Course> findById(Long id);
	
	//POST /: Crear curso
	Course save(Course course);
	
	//metodos opcionales
	Optional<Course> update(Long id, Course course);
	
	Optional<Course> delete(Long id);
	
}
