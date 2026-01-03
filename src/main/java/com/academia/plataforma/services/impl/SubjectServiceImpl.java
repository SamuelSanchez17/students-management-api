package com.academia.plataforma.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academia.plataforma.entities.Subject;
import com.academia.plataforma.repositories.SubjectRepository;
import com.academia.plataforma.services.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService{

	@Autowired
	private SubjectRepository subjectRepository;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Subject> findAll() 
	{
		return subjectRepository.findAll();
	}
	

	@Override
	@Transactional(readOnly = true)
	public Optional<Subject> findById(Long id) 
	{
		return subjectRepository.findById(id);
	}
	

	@Override
	@Transactional
	public Subject save(Subject subject)
	{
		return subjectRepository.save(subject);
	}
	

	@Override
	@Transactional
	public Optional<Subject> update(Long id, Subject subject) 
	{
		Optional<Subject> optionalSubject = subjectRepository.findById(id);
		
		if(optionalSubject.isPresent())
		{
			Subject subjectDB = optionalSubject.orElseThrow();
			
			subjectDB.setSubjectName(subject.getSubjectName());
			subjectDB.setProfessor(subject.getProfessor());
			subjectDB.setCourse(subject.getCourse());
			
			return Optional.of(subjectRepository.save(subjectDB)); // guarda la asignatura actualizada en la base de datos
		}
		
		return optionalSubject;
		
	}

	@Override
	@Transactional
	public Optional<Subject> delete(Long id) 
	{
		
		Optional<Subject> optionalSubject = subjectRepository.findById(id);
		
		optionalSubject.ifPresent(subjectDB -> subjectRepository.delete(subjectDB));
		
		return optionalSubject;
		
	}

	
	
	
	
	@Override
	public boolean existsBysubjectName(String subjectName) {
		// TODO Auto-generated method stub
		return false;
	}

}
