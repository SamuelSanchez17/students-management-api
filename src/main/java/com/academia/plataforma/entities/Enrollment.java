package com.academia.plataforma.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "enrollments")
public class Enrollment {

	@Id
	private EnrollmentID id;
	
	@OneToOne(optional = false)
	@MapsId("studentId")
	@JoinColumn(name = "student_id", nullable = false, unique = true)
	@JsonBackReference
	private Student student;
	
	@ManyToOne(optional = false)
	@MapsId("courseId")
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;
	
	@ManyToOne(optional = false)
	@MapsId("academicTermId")
	@JoinColumn(name = "academic_term_id", nullable = false)
	private AcademicTerm academicTerm;
	
	public Enrollment()
	{
	}

	
	public Enrollment(Student student, Course course, AcademicTerm academicTerm) 
	{
		this.id = new EnrollmentID(student.getId(), course.getId(), academicTerm.getId());
		this.student = student;
		this.course = course;
		this.academicTerm = academicTerm;
	}


	// Getters and Setters
	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	public Course getCourse() {
		return course;
	}


	public void setCourse(Course course) {
		this.course = course;
	}


	public AcademicTerm getAcademicTerm() {
		return academicTerm;
	}


	public void setAcademicTerm(AcademicTerm academicTerm) {
		this.academicTerm = academicTerm;
	}


	public EnrollmentID getId() {
		return id;
	}


	public void setId(EnrollmentID id) {
		this.id = id;
	}
	
}
