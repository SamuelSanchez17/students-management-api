package com.academia.plataforma.Dtos;

public class NoteDTO {
	
	
    private Long studentId;
    private Long subjectId;
    private Long academicTermId;
    private Double value;
    private String observations;
    
	public NoteDTO() {
	}

	public NoteDTO(Long studentId, Long subjectId, Long academicTermId, Double value, String observations) 
	{
		super();
		this.studentId = studentId;
		this.subjectId = subjectId;
		this.academicTermId = academicTermId;
		this.value = value;
		this.observations = observations;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public Long getAcademicTermId() {
		return academicTermId;
	}

	public void setAcademicTermId(Long academicTermId) {
		this.academicTermId = academicTermId;
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
