package com.academia.plataforma.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.academia.plataforma.entities.User;
import com.academia.plataforma.repositories.UserRepository;
import com.academia.plataforma.services.AuthenticationService;
import com.academia.plataforma.utils.ValidationError;

import jakarta.validation.Valid;
import com.academia.plataforma.Dtos.UserDTO;
import com.academia.plataforma.Dtos.RoleDTO;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
    private final UserRepository userRepository;

    public AuthenticationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult result)
	{
		if(result.hasFieldErrors())
			return ValidationError.validation(result);
		
		
        try {
            User savedUser = authenticationService.save(user);
            return ResponseEntity.ok(savedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
      
	}
	
	@GetMapping("/me")
	public ResponseEntity<?> getAuthenticatedUser(Authentication authentication)
	{
		String username = authentication.getName();

        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword()); // mejor no enviar el password

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(user.getRole().getId());
        roleDTO.setRoleName(user.getRole().getRoleName());
        dto.setRole(roleDTO);

        return ResponseEntity.ok(dto);
	}
	
	/*
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
	    try {
	        User authenticatedUser = authenticationService.login(username, password);

	        return ResponseEntity.ok(Map.of(
	                "message", "Inicio de sesión exitoso",
	                "username", authenticatedUser.getUsername(),
	                "role", authenticatedUser.getRole().getRoleName()
	        ));
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(401).body(e.getMessage());
	    }
	}
	
	*/
	

}
