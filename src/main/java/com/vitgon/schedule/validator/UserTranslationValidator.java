package com.vitgon.schedule.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.vitgon.schedule.dto.AddTeacherTranslationDTO;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.auth.User;
import com.vitgon.schedule.service.LocaleService;
import com.vitgon.schedule.service.UserService;

@Component
public class UserTranslationValidator implements Validator {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LocaleService localeService;

	@Override
	public boolean supports(Class<?> clazz) {
		return AddTeacherTranslationDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AddTeacherTranslationDTO addTeacherTranslationDTO = (AddTeacherTranslationDTO) target;
		
		// get user
		User user = userService.findById(addTeacherTranslationDTO.getUserId());
		if (user == null) {
			return;
		}
		
		// get locale
		Locale locale = localeService.findById(addTeacherTranslationDTO.getLocaleId());
		if (locale == null) {
			return;
		}
		
		// check if user translation for this locale already exists
		boolean userTranslationExists = user.getUserTranslations().stream()
			.filter(userTranslation -> locale == userTranslation.getUserTranslationId().getLocale())
			.findFirst()
			.isPresent();
		
		if (!userTranslationExists) {
			errors.rejectValue("localeId", "UniqueTeacherTranslation.teacherTranslation");
		}
	}
}
