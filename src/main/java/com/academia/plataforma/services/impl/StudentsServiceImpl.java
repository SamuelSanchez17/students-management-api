package com.academia.plataforma.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academia.plataforma.entities.Note;
import com.academia.plataforma.entities.Student;
import com.academia.plataforma.repositories.StudentRepository;
import com.academia.plataforma.services.StudentService;

@Service
public class StudentsServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Student> findAll() 
	{
		return studentRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Student> findById(Long id) 
	{
		return studentRepository.findById(id);
	}

	@Override
	@Transactional
	public Student save(Student student) 
	{
		return studentRepository.save(student);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Note> findNoteByStudentId(Long id) 
	{
		return studentRepository.findById(id).map(Student::getNotes)
				.orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
	}

}
