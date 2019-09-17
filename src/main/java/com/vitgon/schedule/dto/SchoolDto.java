package com.vitgon.schedule.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.vitgon.schedule.view.Views;

import java.util.ArrayList;
import java.util.List;


public class SchoolDto {
	
	public SchoolDto() {
	}

	public SchoolDto(int id, String name, String url, String translation, List<MajorDto> majors) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.translation = translation;
		this.majors = majors;
	}

	@JsonView(Views.Public.class)
	private int id;
	
	@JsonView(Views.Public.class)
	private String name;
	
	@JsonView(Views.Public.class)
	private String url;
	
	@JsonView(Views.AdminPanel.class)
	private String translation;
	
	@JsonView(Views.Public.class)
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
