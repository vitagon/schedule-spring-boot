package com.vitgon.schedule.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddTeacherTranslationDTO {
	
	@Min(value = 1, message = "{validation.chooseTeacher}")
	private int userId;
	
	@Min(value = 1, message = "{validation.chooseLocale}")
	private int localeId;
	
	@Size(min = 2, max = 40, message = "{Size.teacherTranslation.lastname}")
	@NotEmpty(message = "{NotEmpty.teacherTranslation.lastname}")
	private String lastname;
	
	@Size(min = 2, max = 40, message = "{Size.teacherTranslation.firstname}")
	@NotEmpty(message = "{NotEmpty.teacherTranslation.firstname}")
	private String firstname;
	
	private String middlename;
}
