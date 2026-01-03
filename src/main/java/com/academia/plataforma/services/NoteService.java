package com.academia.plataforma.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.academia.plataforma.Dtos.NoteDTO;
import com.academia.plataforma.entities.Note;

@Service
public interface NoteService {

	//crea el metodo findById
	Optional<Note> findById(Long id);
	
	
	// POST /nota: Crear una nota
	Note save(Note note);
	
	//GET /asignatura/{id}: Ver notas por asignatura
	List<NoteDTO> findBySubjectId(Long subjectId);
	
	//GET /alumno/{id}: Ver notas por alumno
	List<NoteDTO> findNotesByStudentId(Long studentId);
	
	// PUT /{id}: Editar nota
	Optional<Note> update (Long id, Note note);
	
	// DELETE /{id}: Eliminar nota
	Optional<Note> delete(Long id);
	
	
	
}
