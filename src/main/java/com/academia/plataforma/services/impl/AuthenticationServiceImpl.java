package com.academia.plataforma.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.academia.plataforma.entities.User;
import com.academia.plataforma.repositories.UserRepository;
import com.academia.plataforma.services.AuthenticationService;


@Service
public class AuthenticationServiceImpl implements AuthenticationService{

	
	@Autowired
	private UserRepository userRepository;
	
	 @Autowired
	 private PasswordEncoder passwordEncoder; // Inject PasswordEncoder
	

	@Override
	public User save(User user) 
	{
		if(userRepository.existsByUsername(user.getUsername()))
		{
			throw new RuntimeException("El nombre de usuario ya está en uso.");
		}
		
		user.setEnabled(true); // Habilitar el usuario por defecto al ser creado
		user.setPassword(passwordEncoder.encode(user.getPassword())); // Encriptar la contraseña antes de guardarla
		
		return userRepository.save(user);
	}

	
	//implementar estos metodos con la seguridad de Spring Security
	@Override
	public User getAuthenticatedUser() 
	{
		return null;
	}

	@Override
	public User login(String username, String password)
	{
		
		return null;
	}
	 
}
