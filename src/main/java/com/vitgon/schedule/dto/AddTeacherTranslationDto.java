package com.vitgon.schedule.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.vitgon.schedule.annotation.validation.TranslationEntity;
import com.vitgon.schedule.annotation.validation.UniqueField;
import com.vitgon.schedule.annotation.validation.UniqueTranslation;
import com.vitgon.schedule.group.TranslationGroup;
import com.vitgon.schedule.service.database.UserService;
import com.vitgon.schedule.service.database.translation.UserTranslationService;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@UniqueTranslation(
		uniqueField = @UniqueField(
				field = "userId",
				service = UserService.class
		),
		translationEntity = @TranslationEntity(
			entityField = "user",
			service = UserTranslationService.class
		),
		message = "{Duplicate.translation}",
		groups = TranslationGroup.class
)	
public class AddTeacherTranslationDto {
	
	@Min(value = 1, message = "{validation.chooseTeacher}")
	private Integer userId;
	
	@Min(value = 1, message = "{validation.chooseLocale}")
	private Integer localeId;
	
	@Size(min = 2, max = 40, message = "{Size.default}")
	@NotEmpty(message = "{NotEmpty.default}")
	private String lastname;
	
	@Size(min = 2, max = 40, message = "{Size.default}")
	@NotEmpty(message = "{NotEmpty.default}")
	private String firstname;
	
	private String middlename;
}
