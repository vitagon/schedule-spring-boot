package com.vitgon.schedule.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.vitgon.schedule.annotation.validation.EnumMatch;
import com.vitgon.schedule.annotation.validation.Latin;
import com.vitgon.schedule.annotation.validation.SchoolExists;
import com.vitgon.schedule.annotation.validation.UniqueMajor;
import com.vitgon.schedule.group.OnCreate;
import com.vitgon.schedule.group.OnUpdate;

public class MajorDto {
	private Integer id;
	
	@UniqueMajor(message = "{Duplicate.major}", groups = {OnCreate.class, OnUpdate.class})
	@NotEmpty(message = "{NotEmpty.default}")
	@Size(min = 5, max = 40, message = "{Size.default}")
	@Latin(message = "{Latin.default}")
	private String name;
	private String url;
	
	@EnumMatch(enumClazz = DegreeEnum.class, message = "{Degree.noMatch}")
	private String degree;
	
	@NotNull(message = "{NotEmpty.default}")
	@Min(value = 1, message = "{NotEmpty.default}")
	@Max(value = 6, message = "{Max.default}")
	private Integer duration;
	private String translation;
	
	@SchoolExists
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
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
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
