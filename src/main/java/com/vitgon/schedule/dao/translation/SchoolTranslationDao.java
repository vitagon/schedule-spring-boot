package com.vitgon.schedule.dao.translation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.translation.SchoolTranslation;
import com.vitgon.schedule.model.translation.pk.SchoolTranslationId;

@Repository
public interface SchoolTranslationDao extends JpaRepository<SchoolTranslation, SchoolTranslationId> {
	SchoolTranslation findByTitle(String title);
}
