package com.academia.plataforma.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academia.plataforma.entities.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long>
{
	
	List<Material> findBySubjectId(Long subjectId); // Método para encontrar materiales por ID de asignatura
}
