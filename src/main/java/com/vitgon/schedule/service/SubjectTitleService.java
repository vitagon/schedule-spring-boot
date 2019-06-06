package com.vitgon.schedule.service;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.model.database.translation.SubjectTranslation;
import com.vitgon.schedule.resolver.UrlLocaleResolver;
import com.vitgon.schedule.service.database.translation.SubjectTranslationService;
import com.vitgon.schedule.util.StringUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class SubjectTitleService {

	private SubjectTranslationService subjectTranslationService;
	
	public String getSubjectTitle(Locale locale, Subject subject) {
		if (locale.getCode().equals(UrlLocaleResolver.EN)) {
			return StringUtil.capitalizeFirstLetter(subject.getName());
		}
		
		Optional<SubjectTranslation> subjectTranslation = subjectTranslationService.findByLocaleAndSubject(locale, subject);
		
		if (!subjectTranslation.isPresent()) {
			return StringUtil.capitalizeFirstLetter(subject.getName());
		} else {
			return StringUtil.capitalizeFirstLetter(subjectTranslation.get().getTranslation());
		}
	}
}
