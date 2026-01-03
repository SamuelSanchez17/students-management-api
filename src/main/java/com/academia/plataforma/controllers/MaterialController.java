package com.academia.plataforma.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academia.plataforma.Dtos.MaterialDTO;
import com.academia.plataforma.entities.Material;
import com.academia.plataforma.services.MaterialService;
import com.academia.plataforma.utils.ValidationError;
import java.util.List;

import jakarta.validation.Valid;

import com.academia.plataforma.entities.Professor;
import com.academia.plataforma.entities.Subject;

@RestController
@RequestMapping("/api/materiales")
public class MaterialController {
	
	@Autowired
	private MaterialService materialService;
	
	//crear notacion en la clase MaterialDTO para validar que no se repita nombre de material
	@PostMapping
	public ResponseEntity<?> createMaterial(@Valid @RequestBody MaterialDTO materialRequest, BindingResult result)
	{
		
		if(result.hasFieldErrors())
			return ValidationError.validation(result);
		
		// Crear el objeto Material
	    Material material = new Material();
	    material.setTitle(materialRequest.getTitle());
	    material.setDescription(materialRequest.getDescription());
	    material.setUrl(materialRequest.getUrl());

	    // Asignar Subject y Professor usando los IDs
	    Subject subject = new Subject();
	    subject.setId(materialRequest.getSubjectId());
	    material.setSubject(subject);

	    Professor professor = new Professor();
	    professor.setId(materialRequest.getProfessorId());
	    material.setProfessor(professor);

	    // Guardar el material
	    Material savedMaterial = materialService.save(material);
	    return ResponseEntity.status(HttpStatus.CREATED).body(savedMaterial);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id)
	{
		Optional<Material> materialOptional = materialService.delete(id);
		
		if (materialOptional.isPresent()) 
			return ResponseEntity.ok(materialOptional.get() );
		
		return ResponseEntity.noContent().build();
	}
	
	
	//GET /asignatura/{id}: Ver materiales por asignatura
	@RequestMapping("/asignatura/{id}")
	public ResponseEntity<?> getMaterialsBySubjectId(@PathVariable("id") Long id) 
	{
		 List<Material> materials = materialService.findMaterialsBySubjectId(id);

		if(materials.isEmpty())
		{
			return ResponseEntity.notFound().build();		
		}

		return ResponseEntity.ok(materials);
	}

}
