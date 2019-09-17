package com.vitgon.schedule.service.database.translation;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.model.database.translation.SubjectTranslation;
import com.vitgon.schedule.model.database.translation.pk.SubjectTranslationId;
import com.vitgon.schedule.service.database.base.Service;

import java.util.Optional;

public interface SubjectTranslationService extends Service<SubjectTranslation, SubjectTranslationId> {
	Optional<SubjectTranslation> findByLocaleCodeAndSubjectId(String localeCode, Integer subjectId);
	Optional<SubjectTranslation> findByLocaleAndSubject(Locale locale, Subject subject);
	String getSubjectTitle(Subject subject, Locale locale, boolean substituteNull);
	SubjectTranslation findByTitle(String title);
	Optional<SubjectTranslation> findByLocaleIdAndSubjectId(Integer localeId, Integer subjectId);
	void save(Integer subjectId, Integer localeId, String title);
	void deleteBySubjectIdAndLocaleId(Integer subjectId, Integer localeId);
}
