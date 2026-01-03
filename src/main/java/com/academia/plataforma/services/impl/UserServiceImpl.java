package com.academia.plataforma.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academia.plataforma.entities.User;
import com.academia.plataforma.repositories.UserRepository;
import com.academia.plataforma.services.UserService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() 
	{
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(Long id) 
	{
		
		return userRepository.findById(id);
	}
	

	@Override
	@Transactional
	public Optional<User> update(Long id, User user) 
	{
		
		Optional<User> optionalUser = userRepository.findById(id);
		
		if(optionalUser.isPresent())
		{
			
			User userDB = optionalUser.orElseThrow();
			
			userDB.setUsername(user.getUsername());
			userDB.setEmail(user.getEmail());
			userDB.setPassword(user.getPassword());
			userDB.setRole(user.getRole());
			
			return Optional.of(userRepository.save(userDB)); // guarda el usuario actualizado en la base de datos
		}
		
		return optionalUser;
		
	}

	@Override
	@Transactional
	public Optional<User> delete(Long id) 
	{
		
		Optional<User> optionalUser = userRepository.findById(id);
		
		optionalUser.ifPresent(userDB -> userRepository.delete(userDB) );
		
		return optionalUser;
		
	}

	
	
	
	
	
	
	//modificar metodos si se llegan a necesitar en el futuro
	@Override
	public boolean existsByEmail(String email) 
	{
		
		return false;
	}

	@Override
	public boolean existsByUsername(String username) 
	{
		
		return false;
	}
	
	

}
