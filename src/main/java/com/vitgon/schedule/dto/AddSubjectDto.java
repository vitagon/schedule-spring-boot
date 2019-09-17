package com.vitgon.schedule.dto;

import com.vitgon.schedule.annotation.validation.Latin;
import com.vitgon.schedule.annotation.validation.UniqueSubject;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class AddSubjectDto {

	public AddSubjectDto() {
	}

	public AddSubjectDto(String subjectName) {
		this.subjectName = subjectName;
	}

	@UniqueSubject(message = "{Duplicate.subject}")
	@NotEmpty(message = "{NotEmpty.default}")
	@Size(min = 5, max = 40, message = "{Size.default}")
	@Latin(message = "{Latin.default}")
	private String subjectName;

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
}
