package com.academia.plataforma.services;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.academia.plataforma.entities.Note;
import com.academia.plataforma.entities.Student;

@Service
public interface StudentService {

	//GET student/: Listado de estudiantes
	List<Student> findAll();
	
	//GET student/{id}: Ver detalles de un estudiante
	Optional<Student> findById(Long id);
	
	//POST student/: Crear estudiante
	Student save(Student student);
	
	//GET student/{id}/notas: Ver notas por estudiante
	List<Note> findNoteByStudentId(Long StudentId);
	
	
	// metodos de logica de negocio adicionales
	
	//Optional<Student> update(Long id, Student student);
	
	//Optional<Student> delete(Long id);
	
	//boolean existsByEnrollmentCode(String enrollmentCode);
	
	//Optional<Student> findByEnrollmentCode(String enrollmentCode);
	
}
