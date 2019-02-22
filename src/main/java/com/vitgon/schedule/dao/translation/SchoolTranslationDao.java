package com.vitgon.schedule.dao.translation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.model.database.translation.SchoolTranslation;
import com.vitgon.schedule.model.database.translation.pk.SchoolTranslationId;

@Repository
public interface SchoolTranslationDao extends JpaRepository<SchoolTranslation, SchoolTranslationId> {
	SchoolTranslation findByTitle(String title);
	SchoolTranslation findByLocaleAndSchool(Locale locale, School school);
}
