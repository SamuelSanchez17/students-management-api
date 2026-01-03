package com.academia.plataforma.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academia.plataforma.Dtos.NoteDTO;
import com.academia.plataforma.entities.Note;
import com.academia.plataforma.repositories.NoteRepository;
import com.academia.plataforma.services.NoteService;

@Service
public class NoteServiceImpl implements NoteService{

	@Autowired
	private NoteRepository noteRepository; 
	
	
	@Override
	@Transactional
	public Note save(Note note) 
	{
		return noteRepository.save(note);
	}

	@Override
	@Transactional(readOnly = true)
	public List<NoteDTO> findBySubjectId(Long subjectId) 
	{
		return noteRepository.findBySubjectId(subjectId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<NoteDTO> findNotesByStudentId(Long studentId) 
	{
		return noteRepository.findNotesByStudentId(studentId);
	}

	@Override
	@Transactional
	public Optional<Note> update(Long id, Note note) 
	{
		Optional<Note> noteOptional = noteRepository.findById(id);
		
		if(noteOptional.isPresent())
		{
			Note noteDB = noteOptional.orElseThrow();
			
			noteDB.setStudent(note.getStudent());
			noteDB.setSubject(note.getSubject());
			noteDB.setAcademicTerm(note.getAcademicTerm());
			noteDB.setValue(note.getValue());
			noteDB.setObservations(note.getObservations());
			
			return Optional.of(noteRepository.save(noteDB));
		}
		
		return noteOptional; // si no se encuentra la nota, devuelve un Optional vacío
	}

	@Override
	@Transactional
	public Optional<Note> delete(Long id) 
	{
		Optional <Note> noteOptional = noteRepository.findById(id);
		
		noteOptional.ifPresent(note -> noteRepository.delete(note) );
		
		return noteOptional;
	}

	
	@Override
	@Transactional(readOnly = true)
	public Optional<Note> findById(Long id) 
	{
		return noteRepository.findById(id);
	}

}
