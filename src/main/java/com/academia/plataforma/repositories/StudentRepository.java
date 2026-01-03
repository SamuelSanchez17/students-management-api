package com.academia.plataforma.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.academia.plataforma.entities.Student;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>
{

	//encontrar nota por estudiante
	@Query("SELECT s FROM Student s WHERE s.id = :id")
	Optional<Student> findById(Long id);
	
}
