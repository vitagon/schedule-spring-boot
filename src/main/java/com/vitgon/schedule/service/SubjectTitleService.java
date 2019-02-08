package com.vitgon.schedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Subject;
import com.vitgon.schedule.model.translation.SubjectTranslation;
import com.vitgon.schedule.resolver.UrlLocaleResolver;
import com.vitgon.schedule.service.database.translation.SubjectTranslationService;

@Component
public class SubjectTitleService {

	private SubjectTranslationService subjectTranslationService;

	@Autowired
	public SubjectTitleService(SubjectTranslationService subjectTranslationService) {
		this.subjectTranslationService = subjectTranslationService;
	}
	
	public String getSubjectTitle(Locale locale, Subject subject) {
		if (locale.getCode().equals(UrlLocaleResolver.EN)) {
			return capitalizeFirstLetter(subject.getName());
		}
		
		SubjectTranslation subjectTranslation = subjectTranslationService.findByLocaleAndSubject(locale, subject);
		
		if (subjectTranslation == null) {
			return capitalizeFirstLetter(subject.getName());
		} else {
			return capitalizeFirstLetter(subjectTranslation.getTitle());
		}
	}
	
	public String capitalizeFirstLetter(String word) {
		return word.substring(0,1).toUpperCase() + word.substring(1);
	}
}
