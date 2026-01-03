package com.academia.plataforma.repositories;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.academia.plataforma.entities.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>
{
	
	Optional<Course> findByCourseName(String courseName);
	
}
