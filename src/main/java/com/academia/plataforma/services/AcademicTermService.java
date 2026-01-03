package com.academia.plataforma.services;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.academia.plataforma.entities.AcademicTerm;

@Service
public interface AcademicTermService {
	
	//GET /: Listado periodos académicos
	List<AcademicTerm> findAll();
	
	//GET /{id}: Detalle de periodo por id
	Optional<AcademicTerm> findById(Long id);
	
	//POST /: Crear Periodo Académico
	AcademicTerm save(AcademicTerm academicTerm);
	
	
	//metodos de logica de negocios adicionales
	//PUT /{id}: Editar periodo
	Optional<AcademicTerm> update(Long id, AcademicTerm academicTerm);
	
	//DELETE /{id}: Eliminar periodo
	Optional<AcademicTerm> delete(Long id);
	
}
