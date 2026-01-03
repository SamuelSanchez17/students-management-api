package com.academia.plataforma.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academia.plataforma.entities.Course;
import com.academia.plataforma.repositories.CourseRepository;
import com.academia.plataforma.services.CourseService;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseRepository courseRepository;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Course> findAll() 
	{
		return courseRepository.findAll();
	}

	
	@Override
	@Transactional(readOnly = true)
	public Optional<Course> findById(Long id) 
	{
		return courseRepository.findById(id);
	}

	
	@Override
	@Transactional
	public Course save(Course course) 
	{
		return courseRepository.save(course);
	}

	
	@Override
	@Transactional
	public Optional<Course> update(Long id, Course course) 
	{
		Optional<Course> optionalCourse = courseRepository.findById(id);
		
		if(optionalCourse.isPresent())
		{
			
			Course courseDB = optionalCourse.orElseThrow();

			courseDB.setCourseName(course.getCourseName());
			courseDB.setStudent(course.getStudent());
			courseDB.setSubjects(course.getSubjects());;
			courseDB.setAcademicterms(course.getAcademicterms());

			return Optional.of(courseRepository.save(courseDB)); // guarda el curso actualizado en la base de datos
			
		}
		
		return optionalCourse;
	}

	@Override
	@Transactional
	public Optional<Course> delete(Long id) 
	{
		Optional<Course> optionalCourse = courseRepository.findById(id);
		
		optionalCourse.ifPresent(courseDB -> courseRepository.delete(courseDB));
		
		return optionalCourse;
	}

}
