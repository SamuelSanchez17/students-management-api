package com.academia.plataforma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academia.plataforma.services.ReportService;
import java.util.*;

@RestController
@RequestMapping("/api/reportes")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	
	@GetMapping("/notas-promedio")
	public ResponseEntity<Map<String, Double>> getAverageNotesByCourseAndSubject()
	{
		Map<String, Double> averages = reportService.getAverageNotesByCourseAndSubject();
		return ResponseEntity.ok(averages);
	}
	
	
	//pendiente de revision
	@GetMapping("/historial-estudiante/{id}")
	public ResponseEntity<List<Map<String, Object>>> getStudentHistory(@PathVariable("id") Long StudentId)
	{
		List<Map<String, Object>> history = reportService.getStudentHistory(StudentId);
		
		// Verifica si el historial es nulo y devuelve una lista vacía en su lugar
	    if (history == null) {
	        history = Collections.emptyList();
	    }
		
		return ResponseEntity.ok(history);
	}
	
	
	@GetMapping("/reporte-final/{cursoId}")
	public ResponseEntity<Map<String, Object>> getFinalReportByCourse(@PathVariable("cursoId") Long courseId)
	{
		Map<String, Object> finalReport = reportService.getFinalReportByCourse(courseId);
		
		return ResponseEntity.ok(finalReport);
	}
	
	
}
