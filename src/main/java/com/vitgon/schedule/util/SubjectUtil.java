package com.vitgon.schedule.util;

import java.util.List;
import java.util.stream.Collectors;

import com.vitgon.schedule.dto.SubjectDTO;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Subject;
import com.vitgon.schedule.model.translation.SubjectTranslation;
import com.vitgon.schedule.resolver.UrlLocaleResolver;

public class SubjectUtil {
	
	public static String getSubjectTitle(Subject subject, Locale locale) {
		String subjectTitle;
		
		if (locale.getCode().equals(UrlLocaleResolver.EN)) {
			subjectTitle = subject.getName();
		} else {
			subjectTitle = subject.getSubjectTranslations().stream()
					.filter(x -> locale == x.getSubjectTranslationId().getLocale())
					.map(SubjectTranslation::getTitle)
					.findFirst().get();
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
}
