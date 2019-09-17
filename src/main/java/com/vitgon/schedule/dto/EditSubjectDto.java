package com.vitgon.schedule.dto;

import com.vitgon.schedule.annotation.validation.Latin;
import com.vitgon.schedule.annotation.validation.UniqueSubject;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class EditSubjectDto {

	public EditSubjectDto() {
	}

	public EditSubjectDto(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Min(value = 1, message = "{NotEmpty.default}")
	private int id;
	
	@UniqueSubject(message = "{Duplicate.subject}")
	@NotEmpty(message = "{NotEmpty.default}")
	@Size(min = 5, max = 40, message = "{Size.default}")
	@Latin(message = "{Latin.default}")
	private String name;

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
}
