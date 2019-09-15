package com.vitgon.schedule.dto;

import javax.validation.constraints.Size;

import com.vitgon.schedule.annotation.validation.LocaleExists;
import com.vitgon.schedule.annotation.validation.SchoolExists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class SchoolTranslationDto {

	public SchoolTranslationDto(Integer schoolId, Integer localeId, String translation) {
		super();
		this.schoolId = schoolId;
		this.localeId = localeId;
		this.translation = translation;
	}

	@SchoolExists
	private Integer schoolId;
	
	@LocaleExists
	private Integer localeId;
	
	@Size(min = 3, max = 50, message = "{Size.default}")
	private String translation;

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getLocaleId() {
		return localeId;
	}

	public void setLocaleId(Integer localeId) {
		this.localeId = localeId;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}
}
