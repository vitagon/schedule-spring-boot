package com.vitgon.schedule.dao.translation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.translation.GroupTranslation;
import com.vitgon.schedule.model.translation.pk.GroupTranslationId;

@Repository
public interface GroupTranslationDao extends JpaRepository<GroupTranslation, GroupTranslationId> {
	GroupTranslation findByTitle(String title);
}
