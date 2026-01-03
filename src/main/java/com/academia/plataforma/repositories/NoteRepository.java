package com.academia.plataforma.repositories;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.academia.plataforma.Dtos.NoteDTO;
import com.academia.plataforma.entities.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>
{	
	
	@Query(value = "SELECT new com.academia.plataforma.Dtos.NoteDTO(n.student.id, n.subject.id, n.academicTerm.id, n.value, n.observations) " +
            "FROM Note n WHERE n.student.id = :studentId")
	List<NoteDTO> findNotesByStudentId(@Param("studentId") Long studentId);
	
	
	@Query(value = "SELECT new com.academia.plataforma.Dtos.NoteDTO(n.student.id, n.subject.id, n.academicTerm.id, n.value, n.observations) " +
            "FROM Note n WHERE n.subject.id = :subjectId")
	List<NoteDTO> findBySubjectId(@Param("subjectId") Long subjectId);
	
}
