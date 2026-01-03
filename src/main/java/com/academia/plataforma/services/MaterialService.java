package com.academia.plataforma.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.academia.plataforma.entities.Material;

@Service
public interface MaterialService {

	//POST /: Subir material (NO requiere archivo y metadatos)
	Material save(Material material);
	
	//GET /asignatura/{id}: Ver materiales por asignatura
	List<Material> findMaterialsBySubjectId(Long subjectId);
	
	//DELETE /{id}: Eliminar material
    Optional<Material> delete(Long id);
	
}
