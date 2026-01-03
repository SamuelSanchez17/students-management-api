package com.academia.plataforma.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academia.plataforma.entities.Professor;
import com.academia.plataforma.entities.Subject;
import com.academia.plataforma.repositories.ProfessorRepository;
import com.academia.plataforma.services.ProfessorService;

@Service
public class ProfessorServiceImpl implements ProfessorService{

	@Autowired
	private ProfessorRepository professorRepository;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Professor> findAll() 
	{
		return professorRepository.findAll();
	}
	

	@Override
	@Transactional
	public Professor save(Professor professor) 
	{
		return professorRepository.save(professor);
	}


	@Override
	@Transactional(readOnly = true)
	public Optional<Professor> findById(Long id) 
	{
		return professorRepository.findById(id);
	}


	@Override
	@Transactional(readOnly = true)
	public List<Subject> findSubjectsByProfessorId(Long ProfessorId) 
	{

		Professor professor = professorRepository.findById(ProfessorId)
				.orElseThrow(() -> new RuntimeException("Professor not found with id: " + ProfessorId));
		// Verifica si el profesor existe
		if (professor == null) 
		{
			throw new RuntimeException("Professor not found with id: " + ProfessorId);
		}
		
		
		return professor.getSubjects();
	}
	
	
	
	//implementar si se necesitan
	@Override
	public Optional<Professor> update(Long id, Professor professor) 
	{
		return Optional.empty();
	}

	@Override
	public Optional<Professor> delete(Long id) 
	{

		return Optional.empty();
	}

}
