package com.academia.plataforma.entities;

import jakarta.persistence.*;
import com.academia.plataforma.validations.IsRequired;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name ="role_name", unique = true, nullable = false)
	@IsRequired
	private String roleName;
	
	
	// Constructor por defecto
	public Role() 
	{
	}
	
	// Constructor con parámetros
	public Role(String roleName) 
	{
		this.roleName = roleName;
	}

	
	// Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	
	
}
