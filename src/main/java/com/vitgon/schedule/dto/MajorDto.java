package com.vitgon.schedule.dto;

public class MajorDto {
	private Integer id;
	private String name;
	private String url;
	private DegreeEnum degree;
	private Integer duration;
	private String translation;
	private Integer schoolId;
	private String schoolName;
	
	public MajorDto() {
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public DegreeEnum getDegree() {
		return degree;
	}
	public void setDegree(DegreeEnum degree) {
		this.degree = degree;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public String getTranslation() {
		return translation;
	}
	public void setTranslation(String translation) {
		this.translation = translation;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
}
