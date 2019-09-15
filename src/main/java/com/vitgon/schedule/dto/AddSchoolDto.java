package com.vitgon.schedule.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.vitgon.schedule.annotation.validation.Latin;
import com.vitgon.schedule.annotation.validation.UniqueSchool;

import lombok.Data;
import lombok.NoArgsConstructor;


public class AddSchoolDto {

	public AddSchoolDto() {
	}

	public AddSchoolDto(String name) {
		this.name = name;
	}

	@UniqueSchool(message = "{Duplicate.school}")
	@NotEmpty(message = "{NotEmpty.default}")
	@Size(min = 5, max = 40, message = "{Size.default}")
	@Latin(message = "{Latin.default}")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
