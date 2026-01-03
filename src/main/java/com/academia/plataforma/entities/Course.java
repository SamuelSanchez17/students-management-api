package com.academia.plataforma.entities;

import com.academia.plataforma.validations.IsRequired;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.*;
import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "course_name", unique = true)
	@IsRequired
	private String courseName;
	
	//año academico
	@Column(name = "academic_year")
	private Integer academicYear;
	
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<AcademicTerm> academicterms = new ArrayList<>();
	
	// puede ser @OneToMany o @ManyToMany
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("course")
	private List<Subject> subjects = new ArrayList<>();
	
	@ManyToMany(mappedBy = "courses")
	@JsonIgnoreProperties("courses")
	private Set<Student> student = new HashSet<>();
	
	
	public Course()
	{
		
	}
	

	public Course(String courseName, List<AcademicTerm> academicTerm , List<Subject> subjects, Set<Student> students)
	{
		this.courseName = courseName;
		this.academicterms = academicTerm;
		this.subjects = subjects;
		this.student = students;
	}


	// Getters y Setters
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public List<AcademicTerm> getAcademicterms() {
		return academicterms;
	}


	public void setAcademicterms(List<AcademicTerm> academicterms) {
		this.academicterms = academicterms;
	}


	public List<Subject> getSubjects() {
		return subjects;
	}


	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}


	public Set<Student> getStudent() {
		return student;
	}


	public void setStudent(Set<Student> student) {
		this.student = student;
	}


	public Integer getAcademicYear() {
		return academicYear;
	}


	public void setAcademicYear(Integer academicYear) {
		this.academicYear = academicYear;
	}
	

}
