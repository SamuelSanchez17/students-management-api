package com.academia.plataforma.entities;

import java.util.Date;

import com.academia.plataforma.validations.IsRequired;
import com.academia.plataforma.validations.ValidDateRange;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "academic_terms")
@ValidDateRange //notacion que valida que la fecha de inicio sea anterior a la fecha de fin a nivel de clase
public class AcademicTerm {
	//entidad del periodo lectivo (Año o semestre academico)
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "academic_term_name", nullable = false, unique = true)
	@IsRequired
	private String academicTermName;
	
	@NotNull
	private Date startDate;
	
	@NotNull
	private Date endDate;
	
	
	@ManyToOne(optional = false) // Relación con Course
	@JoinColumn(name = "course_id", nullable = false)
	@JsonIgnoreProperties("academicTerms") // Evita la recursividad infinita al serializar
	@JsonBackReference
	private Course course;
	
	@OneToMany(mappedBy = "academicTerm", cascade = CascadeType.ALL,  orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("academicTerm")
	private List<Note> notes = new ArrayList<>(); // Relacion bidireccional con Note
	
	
	//constructor por defecto
	public AcademicTerm()
	{
	}

	//constructor con parametros
	public AcademicTerm(String academicTermName, Date startDate, Date endDate, Course course, List<Note> notes)
	{
		this.academicTermName = academicTermName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.course = course;
	}

	
	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAcademicTermName() {
		return academicTermName;
	}

	public void setAcademicTermName(String academicTermName) {
		this.academicTermName = academicTermName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	
}
