package com.vitgon.schedule.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.vitgon.schedule.annotation.validation.Latin;
import com.vitgon.schedule.annotation.validation.UniqueSchool;
import com.vitgon.schedule.group.OnCreate;
import com.vitgon.schedule.group.OnUpdate;


public class SchoolDto {

	private Integer id;
	
	@UniqueSchool(message = "{Duplicate.school}", groups = {OnCreate.class, OnUpdate.class})
	@NotEmpty(message = "{NotEmpty.default}")
	@Size(min = 5, max = 40, message = "{Size.default}")
	@Latin(message = "{Latin.default}")
	private String name;
	
	private String url;
	private String translation;
	private List<MajorDto> majors = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public List<MajorDto> getMajors() {
		return majors;
	}

	public void setMajors(List<MajorDto> majors) {
		this.majors = majors;
	}
}
