package com.academia.plataforma.services;

import org.springframework.stereotype.Service;

import com.academia.plataforma.entities.User;

@Service
public interface AuthenticationService {

	//POST /register: Registro de usuarios (admin/profesor/estudiante)
	User save(User user);
	
	//GET /me: Ver datos del usuario autenticado
	User getAuthenticatedUser();
	
	//POST /login: Autenticación de usuarios (admin/profesor/estudiante)
	User login(String username, String password);
	
}
