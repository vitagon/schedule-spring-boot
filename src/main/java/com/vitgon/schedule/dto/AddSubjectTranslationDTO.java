package com.vitgon.schedule.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.vitgon.schedule.annotation.validation.UniqueField;
import com.vitgon.schedule.annotation.validation.UniqueTranslation;
import com.vitgon.schedule.group.TranslationGroup;
import com.vitgon.schedule.service.database.SubjectService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@UniqueTranslation(
//	uniqueField = @UniqueField(
//			field = "subjectId",
//			service = SubjectService.class
//	),
//	message = "{Duplicate.translation}",
//	groups = TranslationGroup.class
//)
public class AddSubjectTranslationDTO {
	
	@Min(value = 1, message = "{validation.chooseSubject}")
	private Integer subjectId;
	
	@Min(value = 1, message = "{validation.chooseLocale}")
	private Integer localeId;
	
	@Size(min = 5, max = 40, message = "{Size.default}")
	@NotEmpty(message = "{NotEmpty.default}")
	private String title;
}
