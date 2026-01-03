package com.academia.plataforma.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academia.plataforma.entities.AcademicTerm;
import com.academia.plataforma.repositories.AcademicTermRepository;
import com.academia.plataforma.services.AcademicTermService;

@Service
public class AcademicTermServiceImpl implements AcademicTermService{

	@Autowired
	private AcademicTermRepository academicTermRepository;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<AcademicTerm> findAll() 
	{
		return academicTermRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<AcademicTerm> findById(Long id) 
	{
		return academicTermRepository.findById(id);
	}

	@Override
	@Transactional
	public AcademicTerm save(AcademicTerm academicTerm) 
	{
		return academicTermRepository.save(academicTerm);
	}
	
	
	
	
	
	//implementar si se necesitan

	@Override
	public Optional<AcademicTerm> update(Long id, AcademicTerm academicTerm) 
	{
		return Optional.empty();
	}

	@Override
	public Optional<AcademicTerm> delete(Long id) 
	{
		return Optional.empty();
	}

}
