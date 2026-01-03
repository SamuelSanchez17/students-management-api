package com.academia.plataforma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.academia.plataforma.entities.Role;
import com.academia.plataforma.repositories.RoleRepository;

@Component
public class RoleInitializer implements CommandLineRunner{

	//Esta clase inicializa los roles en la DB al momento de correr la aplicacion
	
	@Autowired
	private final RoleRepository roleRepository;
	
	public RoleInitializer(RoleRepository roleRepository) 
	{
		this.roleRepository = roleRepository;
	}
	
	
	
	@Override
	public void run(String... args) throws Exception 
	{
		if(roleRepository.findByRoleName("ROLE_ADMIN") == null)
		{
			roleRepository.save(new Role("ROLE_ADMIN") );
		}
		if(roleRepository.findByRoleName("ROLE_PROFESOR") == null)
		{
			roleRepository.save(new Role("ROLE_PROFESOR") );
		}
		if(roleRepository.findByRoleName("ROLE_ESTUDIANTE") == null)
		{
			roleRepository.save(new Role("ROLE_ESTUDIANTE") );
		}
		
	}

}
