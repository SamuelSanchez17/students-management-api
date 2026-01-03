package com.academia.plataforma.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.academia.plataforma.entities.Subject;

@Service
public interface SubjectService {

	//GET /: Listado de asignaturas
	List<Subject> findAll();
	
	//GET /{id}: Detalle de una asignatura
	Optional<Subject> findById(Long id);
	
	//POST /: Crear asignatura
	Subject save(Subject subject);
	
	//PUT /{id}: Editar asignatura
	Optional<Subject> update(Long id, Subject subject);
	
	//DELETE /{id}: Eliminar asignatura
	Optional<Subject> delete(Long id);
	
	
	
	// Métodos de logica de negocio adicionales
	boolean existsBysubjectName(String subjectName);
	
	
	
}
