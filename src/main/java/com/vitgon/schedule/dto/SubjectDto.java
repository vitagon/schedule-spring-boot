package com.vitgon.schedule.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.vitgon.schedule.view.Views;


public class SubjectDto {
	
	public SubjectDto(Integer id, String name, String translation) {
		super();
		this.id = id;
		this.name = name;
		this.translation = translation;
	}

	public SubjectDto() {
	}

	@JsonView(Views.Public.class)
	private Integer id;
	
	@JsonView(Views.Public.class)
	private String name;
	
	@JsonView(Views.AdminPanel.class)
	private String translation;

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

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}
}
