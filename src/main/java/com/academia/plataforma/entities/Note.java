package com.academia.plataforma.entities;

import com.academia.plataforma.validations.IsRequired;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "notes")
@JsonIgnoreProperties({"academicTerm"})
public class Note {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id", nullable = false)
	@JsonIgnore // Evita serializar el estudiante en las notas
	private Student student;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "subject_id", nullable = false)
	private Subject subject;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "academic_term_id", nullable = false)
	@JsonBackReference
	private AcademicTerm academicTerm;
	
	@Column(name = "value", nullable = false)
	//@IsRequired
	private Double value;
	
	@Column(name = "observations", nullable = false)
	@IsRequired
	private String observations;

	public Note()
	{
	}
	
	public Note(Student student, Subject subject, AcademicTerm academicTerm, Double value, String observations) 
	{
		this.student = student;
		this.subject = subject;
		this.academicTerm = academicTerm;
		this.value = value;
		this.observations = observations;
	}
	
	
    // Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public AcademicTerm getAcademicTerm() {
		return academicTerm;
	}

	public void setAcademicTerm(AcademicTerm academicTerm) {
		this.academicTerm = academicTerm;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}
	
	
	
}
