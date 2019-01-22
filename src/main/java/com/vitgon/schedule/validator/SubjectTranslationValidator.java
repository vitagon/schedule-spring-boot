package com.vitgon.schedule.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.vitgon.schedule.dto.AddSubjectTranslationDTO;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Subject;
import com.vitgon.schedule.service.LocaleService;
import com.vitgon.schedule.service.SubjectService;

// TODO: Instead of this class, create annotation 
// 		 https://www.baeldung.com/spring-mvc-custom-validator
@Component
public class SubjectTranslationValidator implements Validator {
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private LocaleService localeService;

	@Override
	public boolean supports(Class<?> clazz) {
		return AddSubjectTranslationDTO.class.isAssignableFrom(clazz);
	}


	@Override
	public void validate(Object target, Errors errors) {
		AddSubjectTranslationDTO addSubjectTranslationDTO = (AddSubjectTranslationDTO) target;
		if (addSubjectTranslationDTO.getSubjectId() <= 0) {
			return;
		}
		if (addSubjectTranslationDTO.getLocaleId() <= 0) {
			return;
		}
		if (addSubjectTranslationDTO.getTitle().equals("")) {
			return;
		}
		
		Subject subject = subjectService.findById(addSubjectTranslationDTO.getSubjectId());
		if (subject == null) {
			return;
		}
		
		Locale locale = localeService.findById(addSubjectTranslationDTO.getLocaleId());
		if (locale == null) {
			return;
		}
		boolean translationExists = subject.getSubjectTranslations().stream()
			.filter(subjectTranslation -> locale == subjectTranslation.getSubjectTranslationId().getLocale())
			.findFirst()
			.isPresent();
		
		if (translationExists) {
			errors.rejectValue("localeId", "Duplicate.translation");
		}
	}
}
