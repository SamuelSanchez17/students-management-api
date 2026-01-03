package com.academia.plataforma.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academia.plataforma.entities.AcademicTerm;

@Repository
public interface AcademicTermRepository extends JpaRepository<AcademicTerm, Long>
{

	// JpaRepository proporciona métodos predefinidos para realizar operaciones de base de datos
	// como guardar, buscar, actualizar y eliminar registros.

	// No es necesario agregar métodos adicionales a menos que se requieran consultas personalizadas.

}
