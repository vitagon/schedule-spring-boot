package com.vitgon.schedule.dto;

import com.vitgon.schedule.annotation.validation.LocaleExists;
import com.vitgon.schedule.annotation.validation.SubjectExists;

import javax.validation.constraints.Size;


public class SubjectTranslationDto {
	
	public SubjectTranslationDto(Integer subjectId, Integer localeId, String translation) {
		super();
		this.subjectId = subjectId;
		this.localeId = localeId;
		this.translation = translation;
	}

	@SubjectExists
	private Integer subjectId;
	
	@LocaleExists
	private Integer localeId;
	
	@Size(min = 3, max = 50, message = "{Size.default}")
	private String translation;

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
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
