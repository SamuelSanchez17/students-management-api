package com.academia.plataforma.entities;

import java.util.List;

import com.academia.plataforma.validations.IsRequired;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "professors")
public class Professor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(optional = false)
	@JoinColumn(name = "user_id", nullable = false, unique = true)
	private User user;
	
	@Column
	@IsRequired
	private String specialty;
	
	@OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("professor")
	private List<Subject> subjects;
	
	
	// Constructor por defecto
	public Professor() 
	{
	}
	
	public Professor(User user, String specialty, List<Subject> subjects) 
	{
		this.user = user;
		this.specialty = specialty;
		this.subjects = subjects;
	}

	
	// Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	
	
}
