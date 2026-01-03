package com.academia.plataforma.Dtos;

public class MaterialDTO {

    private String title;
    private String description;
    private String url;
    private Long subjectId;
    private Long professorId;
    
	public MaterialDTO() {
	}

	public MaterialDTO(String title, String description, String url, Long subjectId, Long professorId) {
		super();
		this.title = title;
		this.description = description;
		this.url = url;
		this.subjectId = subjectId;
		this.professorId = professorId;
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

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public Long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}
	
	
	
}
