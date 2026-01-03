package com.academia.plataforma.services.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academia.plataforma.Dtos.NoteDTO;
import com.academia.plataforma.entities.Note;
import com.academia.plataforma.repositories.CourseRepository;
import com.academia.plataforma.repositories.NoteRepository;
import com.academia.plataforma.services.ReportService;

@Service
public class ReportServiceImpl implements ReportService{

	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private NoteRepository noteRepository;
	
	
	@Override
	public List<Map<String, Object>> getStudentHistory(Long studentId) 
	{
		
		if (studentId == null) {
	        throw new IllegalArgumentException("El ID del estudiante no puede ser nulo");
	    }

	    List<NoteDTO> notes = noteRepository.findNotesByStudentId(studentId);

	    // retorna una lista vacia en caso de que no existan notas
	    if (notes == null || notes.isEmpty()) {
	        return List.of();
	    }

	    return notes.stream()
	        .filter(note -> note != null)
	        .collect(Collectors.groupingBy(
	            note -> Map.of(
	                "Academic Term", note.getAcademicTermId() != null ? note.getAcademicTermId() : "Desconocido"
	            ),
	            Collectors.mapping(
	                note -> Map.of(
	                    "subject", note.getSubjectId() != null ? note.getSubjectId() : "Desconocido",
	                    "value", note.getValue() != null ? note.getValue() : 0,
	                    "observations", note.getObservations() != null ? note.getObservations() : "No observations"
	                ),
	                Collectors.toList()
	            )
	        ))
	        .entrySet().stream()
	        .map(entry -> Map.of(
	            "course", entry.getKey().get("course") != null ? entry.getKey().get("course") : "Unknown",
	            "academicTerm", entry.getKey().get("Academic Term"),
	            "notes", entry.getValue()
	        ))
	        .collect(Collectors.toList());
	}

	@Override
	public Map<String, Object> getFinalReportByCourse(Long courseId) 
	{
	    return courseRepository.findById(courseId).map(course -> {
	        // Construyendo el reporte final por curso
	        return Map.of(
	            "courseName", course.getCourseName(),
	            "academicTerms", course.getAcademicterms().stream()
	                .map(term -> Map.of(
	                    "academicTermName", term.getAcademicTermName(),
	                    "subjects", term.getNotes().stream()
	                        .collect(Collectors.groupingBy(
	                            note -> note.getSubject().getSubjectName(),
	                            Collectors.averagingDouble(Note::getValue)
	                        ))
	                ))
	                .collect(Collectors.toList())
	        );
	    }).orElseThrow(() -> new IllegalArgumentException("Course not found"));
	}

	@Override
	public Map<String, Double> getAverageNotesByCourseAndSubject() 
	{
		return noteRepository.findAll().stream()
		        .collect(Collectors.groupingBy(
		            note -> 
		            {
		                String courseName = note.getAcademicTerm().getCourse().getCourseName();
		                String subjectName = note.getSubject().getSubjectName();
		                return courseName + " - " + subjectName;
		            },
		            Collectors.averagingDouble(Note::getValue)
		        ));
	}

}
