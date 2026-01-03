package com.academia.plataforma.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface ReportService {

	//GET /notas-promedio: Promedio por curso y asignatura
	Map<String, Double> getAverageNotesByCourseAndSubject();
	
	//GET /historial-estudiante/{id}: Historial completo de notas
    List<Map<String, Object>> getStudentHistory(Long studentId);
	
	//GET /reporte-final/{cursoId}: Consolidado por curso
	Map<String, Object> getFinalReportByCourse(Long courseId);
    
}
