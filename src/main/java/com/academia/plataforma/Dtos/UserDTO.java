package com.academia.plataforma.Dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDTO {

	
	@NotNull
	@Size(min = 4, max = 40, message = "el usuarios debe tener entre 4 y 40 caracteres")
	private String username;
	
	@NotNull(message = "El email es obligatorio")
	//@Email
	@Size(min = 10, max = 50, message = "El email no puede tener menos de 10 caracteres ni exceder los 50")
	private String email;
	
    @NotNull(message = "El password es obligatorio")
    private String password;
    
	private RoleDTO role;
	
	
	// Constructor por defecto
	public UserDTO()
	{
	}

	
	public UserDTO(String username, String email, String password, RoleDTO role) 
	{
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
	}




	// Getters y Setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public RoleDTO getRole() {
		return role;
	}


	public void setRole(RoleDTO role) {
		this.role = role;
	}
	
	
}
