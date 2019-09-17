package com.vitgon.schedule.dto;

import com.vitgon.schedule.annotation.validation.Latin;
import com.vitgon.schedule.annotation.validation.UniqueSchool;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class EditSchoolDto {

	public EditSchoolDto() {
	}

	public EditSchoolDto(int schoolId, String newSchoolName) {
		this.schoolId = schoolId;
		this.newSchoolName = newSchoolName;
	}

	@Min(value = 1, message = "{NotEmpty.default}")
	private int schoolId;
	
	@UniqueSchool(message = "{Duplicate.school}")
	@NotEmpty(message = "{NotEmpty.default}")
	@Size(min = 5, max = 40, message = "{Size.default}")
	@Latin(message = "{Latin.default}")
	private String newSchoolName;

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public String getNewSchoolName() {
		return newSchoolName;
	}

	public void setNewSchoolName(String newSchoolName) {
		this.newSchoolName = newSchoolName;
	}
}
