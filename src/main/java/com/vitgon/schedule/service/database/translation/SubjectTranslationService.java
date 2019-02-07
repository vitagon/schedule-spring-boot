package com.vitgon.schedule.service.database.translation;

import java.util.List;

import com.vitgon.schedule.dto.SubjectDTO;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Subject;
import com.vitgon.schedule.model.translation.SubjectTranslation;
import com.vitgon.schedule.model.translation.pk.SubjectTranslationId;
import com.vitgon.schedule.service.database.base.Service;

public interface SubjectTranslationService extends Service<SubjectTranslation, SubjectTranslationId> {
	SubjectTranslation findByLangCodeAndSubjectId(String langCode, int subjectId);
	SubjectTranslation findByLocaleAndSubject(Locale locale, Subject subject);
	String getSubjectTitle(Subject subject, Locale locale);
	SubjectTranslation findByTitle(String title);
}
