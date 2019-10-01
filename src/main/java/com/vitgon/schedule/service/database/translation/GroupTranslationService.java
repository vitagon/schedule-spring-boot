package com.vitgon.schedule.service.database.translation;

import java.util.Optional;

import com.vitgon.schedule.model.database.translation.GroupTranslation;
import com.vitgon.schedule.model.database.translation.pk.GroupTranslationId;
import com.vitgon.schedule.service.database.base.Service;

public interface GroupTranslationService extends Service<GroupTranslation, GroupTranslationId>{
	Optional<GroupTranslation> findByLocaleIdAndGroupId(Integer localeId, Integer groupId);

	void save(Integer groupId, Integer localeId, String translation);
}
