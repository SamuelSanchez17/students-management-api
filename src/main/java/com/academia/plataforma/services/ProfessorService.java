package com.academia.plataforma.services;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.academia.plataforma.entities.Professor;
import com.academia.plataforma.entities.Subject;

@Service
public interface ProfessorService {
	
	//GET /: Listado de profesores
	List<Professor> findAll();
	
	//GET /{id}: Ver detalles de un profesor
	Optional<Professor> findById(Long id);
	
	//POST /: Crear profesor
	Professor save(Professor professor);
	
	//GET /{id}/asignaturas: Ver asignaturas asignadas
	List<Subject> findSubjectsByProfessorId(Long ProfessorId);
	
	
	// Metodos de logica de negocio si es necesario
	Optional<Professor> update(Long id, Professor professor);
	
	Optional<Professor> delete(Long id);
	
	
}
