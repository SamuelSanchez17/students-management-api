package com.academia.plataforma.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academia.plataforma.entities.Material;
import com.academia.plataforma.repositories.MaterialRepository;
import com.academia.plataforma.services.MaterialService;

@Service
public class MaterialServiceImpl implements MaterialService{

	@Autowired
	private MaterialRepository materialRepository;
	
	
	@Override
	@Transactional
	public Material save(Material material) 
	{
		return materialRepository.save(material);
	}

	
	@Override
	@Transactional(readOnly = true)
	public List<Material> findMaterialsBySubjectId(Long subjectId) 
	{
		return materialRepository.findBySubjectId(subjectId);
	}
	

	@Override
	@Transactional
	public Optional<Material> delete(Long id) 
	{
		Optional<Material> optionalMaterial = materialRepository.findById(id);
		
		optionalMaterial.ifPresent(materialDB -> materialRepository.delete(materialDB));
		
		return optionalMaterial;
	}

}
