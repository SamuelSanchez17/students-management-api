package com.academia.plataforma.entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class EnrollmentID implements Serializable{

	 private Long studentId;
	 private Long courseId;
	 private Long academicTermId ;

	 // Constructor vacío
	 public EnrollmentID() 
	 {
		 
	 }

	public EnrollmentID(Long studentId, Long courseId, Long academicTermId) 
	{
		this.studentId = studentId;
		this.courseId = courseId;
		this.academicTermId = academicTermId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getAcademicTermId() {
		return academicTermId;
	}

	public void setAcademicTermId(Long academicTermId) {
		this.academicTermId = academicTermId;
	}
	
	
}
