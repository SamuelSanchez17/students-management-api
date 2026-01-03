package com.academia.plataforma.entities;

import com.academia.plataforma.validations.IsRequired;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "subjects")
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@IsRequired
	@Size(max = 100)
	private String subjectName;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "professor_id", nullable = false)
	@JsonIgnoreProperties("subjects")
	private Professor professor;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "course_id", nullable = false)
	@JsonIgnoreProperties("subjects")
	private Course course;
	
	// Constructor por defecto
	public Subject() 
	{
	}

	public Subject( @Size(max = 100) String subjectName, Professor professor, Course course) 
	{
		this.subjectName = subjectName;
		this.professor = professor;
		this.course = course;
	}

	
	// Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	
}
