package com.vitgon.schedule.dto;

import javax.validation.constraints.Size;

import com.vitgon.schedule.annotation.validation.LocaleExists;
import com.vitgon.schedule.annotation.validation.SubjectExists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectTranslationDto {
	
	@SubjectExists
	private Integer subjectId;
	
	@LocaleExists
	private Integer localeId;
	
	@Size(min = 3, max = 50, message = "{Size.default}")
	private String translation;
}
