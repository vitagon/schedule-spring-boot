package com.vitgon.schedule.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddSubjectTranslationDTO {
	
	@Min(value = 1, message = "{validation.chooseSubject}")
	private int subjectId;
	
	@Min(value = 1, message = "{validation.chooseLocale}")
	private int localeId;
	
	@Size(min = 5, max = 40, message = "{Size.default}")
	@NotEmpty(message = "{NotEmpty.default}")
	private String title;
}
