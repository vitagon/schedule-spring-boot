package com.vitgon.schedule.util;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Subject;
import com.vitgon.schedule.model.translation.SubjectTranslation;

public class SubjectUtil {
	
	public static String getSubjectTitle(Subject subject, Locale locale) {
		return subject.getSubjectTranslations().stream()
				.filter(x -> locale == x.getLocale())
				.map(SubjectTranslation::getTitle)
				.findFirst().get();
	}
}
