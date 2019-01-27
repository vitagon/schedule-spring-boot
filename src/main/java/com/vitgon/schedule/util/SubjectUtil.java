package com.vitgon.schedule.util;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.vitgon.schedule.dto.SubjectDTO;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Subject;
import com.vitgon.schedule.model.translation.SubjectTranslation;
import com.vitgon.schedule.resolver.UrlLocaleResolver;

public class SubjectUtil {
	
	/**
	 * Get subject title translation
	 * If translation doesn't exist then take default English title
	 * 
	 * @param subject
	 * @param locale
	 * @return
	 */
	public static String getSubjectTitle(Subject subject, Locale locale) {
		String subjectTitle = null;
		Optional<String> subjectTitleOptional = null;
		
		if (locale.getCode().equals(UrlLocaleResolver.EN)) {
			subjectTitle = subject.getName();
		} else {
			subjectTitleOptional = subject.getTranslations().stream()
					.filter(x -> locale == x.getSubjectTranslationId().getLocale())
					.map(SubjectTranslation::getTitle)
					.findFirst();
			
			if (!subjectTitleOptional.isPresent()) {
				subjectTitle = subject.getName();
			}
		}
		
		return subjectTitle.substring(0, 1).toUpperCase() + subjectTitle.substring(1);
	}
	
	public static List<SubjectDTO> mapToSubjectDTOList(List<Subject> subjects) {
		return subjects.stream()
				.map(subject -> {
					return new SubjectDTO(subject.getId(), subject.getName());
				})
				.collect(Collectors.toList());
	}
	
	public static List<SubjectDTO> mapToSubjectDTOList(List<Subject> subjects, Locale locale) {
		return subjects.stream()
				.map(subject -> {
					return new SubjectDTO(subject.getId(), subject.getName(), getSubjectTitle(subject, locale));
				})
				.collect(Collectors.toList());
	}
}
