package com.academia.plataforma.entities;

//import com.academia.plataforma.validations.IsExistsUsername;
import com.academia.plataforma.validations.IsRequired;
//import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	@IsRequired(message = "El username es obligatorio") 
	@Size(min = 4, max = 40)
	//@IsExistsUsername
	private String username;
	
	@Size(min = 10, max = 50)
	@IsRequired(message = "El email es obligatorio") 
	private String email;
	
	@Size(min = 4, max = 255)
	//@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@IsRequired(message = "La contraseña es obligatoria") 
	private String password;
	
	// un usuario puede tener un rol, y un rol le puede pertenecer a varios usuarios
	@ManyToOne 
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;
	
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private boolean enabled;
	
	
	//Constructor por defecto
	public User()
	{
		
	}
	
	//Constructor con parámetros
	public User(String username, String email, String password, Role role) 
	{
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	
	//Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
	
}
