package com.academia.plataforma.Dtos;

import jakarta.validation.constraints.NotNull;

public class RoleDTO {
	 @NotNull(message = "El ID del rol es obligatorio")
	 private Long id;

	 private String roleName;

	 // Default constructor
	 public RoleDTO() {}

	// Parameterized constructor
	public RoleDTO(Long id, String roleName) 
	{
		this.id = id;
		this.roleName = roleName;
	}

	// Getters and Setters
	public Long getId() 
	{
	    return id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setId(Long id) {
		this.id = id;
	}
 

	    
}
