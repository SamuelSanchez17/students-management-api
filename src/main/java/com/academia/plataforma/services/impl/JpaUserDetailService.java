package com.academia.plataforma.services.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academia.plataforma.repositories.UserRepository;
import com.academia.plataforma.entities.User;
import com.academia.plataforma.entities.Role;

@Service
public class JpaUserDetailService implements UserDetailsService 
{

	@Autowired
	private UserRepository userRepository; 
	
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		Optional<User> userOptional = userRepository.findByUsername(username);
		
		if(userOptional.isEmpty())
		{
			throw new UsernameNotFoundException(String.format("El Username %s no existe en el sistema ", username));
		}
		
		User user = userOptional.orElseThrow();
		Role role = user.getRole();
		
		if (role == null) 
		{
            throw new UsernameNotFoundException(String.format("El usuario %s no tiene un rol asignado", username));
        }
		
		List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role.getRoleName()));
		
		
		return new org.springframework.security.core.userdetails.User(
	            user.getUsername(),
	            user.getPassword(),
                user.isEnabled(),
	            true,
	            true,
	            true,
	            authorities
	            );
		
	}

}
