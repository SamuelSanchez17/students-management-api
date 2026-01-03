package com.academia.plataforma.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academia.plataforma.Dtos.NoteDTO;
import com.academia.plataforma.entities.Note;
import com.academia.plataforma.entities.Student;
import com.academia.plataforma.entities.Subject;
import com.academia.plataforma.entities.AcademicTerm;
import com.academia.plataforma.services.NoteService;
import com.academia.plataforma.utils.ValidationError;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/notas")
public class NoteController {

	
	@Autowired
	private NoteService noteService;
	
	
	@RequestMapping("/asignatura/{id}")
	ResponseEntity<List<?>> getNoteBySubjectId(@PathVariable("id") Long subjectId)
	{
		
		List<NoteDTO> notes = noteService.findBySubjectId(subjectId);
		
		if(notes.isEmpty())
		{
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(notes);
	}
	
	
    @RequestMapping("/estudiante/{id}")
    ResponseEntity<List<?>> getNoteByStudentId(@PathVariable("id") Long studentId)
    {
    	List<NoteDTO> notes = noteService.findNotesByStudentId(studentId);
    	
    	if(notes.isEmpty())
    	{
    		return ResponseEntity.notFound().build();
    	}
    	
    	return ResponseEntity.ok(notes);
    }
    
    @PostMapping
    ResponseEntity<?> createNote(@Valid @RequestBody NoteDTO noteDTO, BindingResult result)
    {
    	if(result.hasFieldErrors())
			return ValidationError.validation(result);
    	
    	 // Map NoteDTO to Note entity
        Note note = new Note();

        // Assign related entities using IDs from the DTO
        Student student = new Student();
        student.setId(noteDTO.getStudentId());
        note.setStudent(student);

        Subject subject = new Subject();
        subject.setId(noteDTO.getSubjectId());
        note.setSubject(subject);

        AcademicTerm academicTerm = new AcademicTerm();
        academicTerm.setId(noteDTO.getAcademicTermId());
        note.setAcademicTerm(academicTerm);

        // Assign other fields
        note.setValue(noteDTO.getValue());
        note.setObservations(noteDTO.getObservations());

        // Save the note
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.save(note));
    }
    
    
    @PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @PathVariable("id") Long id, @RequestBody NoteDTO noteDTO, BindingResult result)
	{
		
		if(result.hasFieldErrors())
			return ValidationError.validation(result);
		
		 Optional<Note> noteOptional = noteService.findById(id);
		    if (noteOptional.isEmpty()) {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND)
		                .body("Note with ID " + id + " not found.");
		    }

		    // Map NoteDTO to Note entity
		    Note note = noteOptional.get();
		    Student student = new Student();
		    student.setId(noteDTO.getStudentId());
		    note.setStudent(student);

		    Subject subject = new Subject();
		    subject.setId(noteDTO.getSubjectId());
		    note.setSubject(subject);

		    AcademicTerm academicTerm = new AcademicTerm();
		    academicTerm.setId(noteDTO.getAcademicTermId());
		    note.setAcademicTerm(academicTerm);

		    note.setValue(noteDTO.getValue());
		    note.setObservations(noteDTO.getObservations());

		    // Save the updated note
		    Note updatedNote = noteService.save(note);
		    return ResponseEntity.ok(updatedNote);
		
	}
    
    
    @DeleteMapping("/{noteId}")
    ResponseEntity<?> delete(@PathVariable("noteId") Long id)
    {
    	Optional<Note> noteOptional = noteService.findById(id);

        if (noteOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Note with ID " + id + " not found.");
        }

        noteService.delete(id);
        return ResponseEntity.ok("Note with ID " + id + " deleted successfully.");
    }
    
	
}
