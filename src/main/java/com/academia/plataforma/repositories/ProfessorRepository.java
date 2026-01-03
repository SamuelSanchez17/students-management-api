package com.academia.plataforma.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academia.plataforma.entities.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>
{

}
