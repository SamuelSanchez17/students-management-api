package com.academia.plataforma.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.academia.plataforma.entities.User;

@Service
public interface UserService {

	//operaciones CRUD para usuarios
	
	//GET /: Listado de todos los usuarios
	List<User> findAll();
	
	//GET /{id}: Detalle de un usuario
	Optional<User> findById(Long id);
	
	//PUT /{id}: Editar usuario
	Optional<User> update(Long id, User user);
	
	//DELETE /{id}: Eliminar usuario
	Optional<User> delete(Long id);
	
	
	//metodos de logica de negocio
	boolean existsByEmail(String email); //verifica si existe un usuario por email
	
	boolean existsByUsername(String username); //verifica si existe un usuario por username
	
}
