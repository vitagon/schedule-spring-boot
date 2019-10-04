package com.vitgon.schedule.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.vitgon.schedule.annotation.validation.Latin;
import com.vitgon.schedule.annotation.validation.UniqueSubject;
import com.vitgon.schedule.group.OnCreate;
import com.vitgon.schedule.group.OnUpdate;

public class SubjectDto {

	private Integer id;
	
	@UniqueSubject(message = "{Duplicate.subject}", groups = {OnCreate.class, OnUpdate.class})
	@NotEmpty(message = "{NotEmpty.default}")
	@Size(min = 5, max = 40, message = "{Size.default}")
	@Latin(message = "{Latin.default}")
	private String name;
	
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
