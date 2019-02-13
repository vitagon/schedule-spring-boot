package com.vitgon.schedule.service.database.translation;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.model.database.translation.SubjectTranslation;
import com.vitgon.schedule.model.database.translation.pk.SubjectTranslationId;
import com.vitgon.schedule.service.database.base.Service;

public interface SubjectTranslationService extends Service<SubjectTranslation, SubjectTranslationId> {
	SubjectTranslation findByLangCodeAndSubjectId(String langCode, int subjectId);
	SubjectTranslation findByLocaleAndSubject(Locale locale, Subject subject);
	String getSubjectTitle(Subject subject, Locale locale);
	SubjectTranslation findByTitle(String title);
}
