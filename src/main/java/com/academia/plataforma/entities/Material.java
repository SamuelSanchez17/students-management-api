package com.academia.plataforma.entities;

import com.academia.plataforma.validations.IsRequired;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "materials")
public class Material {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@IsRequired
	private String title;

	@Column(nullable = false)
	@IsRequired
	private String description;
	
	@Column(nullable = false)
	@IsRequired
    private String url;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "subject_id", nullable = false)
	@JsonIgnore
    private Subject subject;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "professor_id", nullable = false)
	@JsonIgnore
    private Professor professor;
	
	// Constructor por defecto
	public Material() 
	{
	}
	
	public Material(String title, String description, String url, Subject subject, Professor professor) 
	{
		this.title = title;
		this.description = description;
		this.url = url;
		this.subject = subject;
		this.professor = professor;
	}

	
	// Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	
	
}
