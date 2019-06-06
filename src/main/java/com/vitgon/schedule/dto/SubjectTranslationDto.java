package com.vitgon.schedule.dto;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectTranslationDto {
	
	private Integer subjectId;
	private Integer localeId;
	
	@Size(min = 3, max = 50, message = "{Size.default}")
	private String translation;
}
