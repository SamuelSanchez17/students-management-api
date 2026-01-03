package com.academia.plataforma.entities;

//import com.academia.plataforma.validations.IsRequired;

import jakarta.persistence.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "enrollment_code", nullable = false, unique = true)
	private String enrollmentCode; // Codigo de matricula del estudiante;
	
	@OneToOne(mappedBy = "student", optional = false, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Enrollment enrollment;
	
	@OneToOne(optional = false)
	@JoinColumn(name = "user_id", nullable = false, unique = true)
	private User user;
	
	@ManyToMany()
	@JoinTable(
		name = "student_courses",
		joinColumns = @JoinColumn(name = "student_id"),
		inverseJoinColumns = @JoinColumn(name = "course_id")
	)
	@JsonIgnoreProperties("student") // Evita serializar la relación inversa
	private Set<Course> courses = new HashSet<>();
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore // Evita serializar las notas del estudiante
	private List<Note> notes; //relacion bidireccional con la entidad Note
	
	
	// Constructor por defecto
	public Student() 
	{
	}

	
	public Student(User user, Enrollment enrollments , Set<Course> courses)
	{
		this.user = user;
		this.enrollment = enrollments;
		this.courses = courses;
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


	public Enrollment getEnrollment() {
		return enrollment;
	}


	public void setEnrollment(Enrollment enrollment) {
		this.enrollment = enrollment;
	}


	public Set<Course> getCourses() {
		return courses;
	}


	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}


	public List<Note> getNotes() {
		return notes;
	}


	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}


	public String getEnrollmentCode() {
		return enrollmentCode;
	}


	public void setEnrollmentCode(String enrollmentCode) {
		this.enrollmentCode = enrollmentCode;
	}
	
}
