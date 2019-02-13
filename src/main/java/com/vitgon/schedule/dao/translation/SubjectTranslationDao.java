package com.vitgon.schedule.dao.translation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.model.database.translation.SubjectTranslation;
import com.vitgon.schedule.model.database.translation.pk.SubjectTranslationId;

@Repository
public interface SubjectTranslationDao extends JpaRepository<SubjectTranslation, SubjectTranslationId> {
	SubjectTranslation findByTitle(String title);
	SubjectTranslation findBySubjectTranslationIdLocaleAndSubjectTranslationIdSubject(Locale locale, Subject subject);
}
