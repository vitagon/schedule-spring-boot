package com.vitgon.schedule.service.database.translation;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.model.database.translation.UserTranslation;
import com.vitgon.schedule.model.database.translation.pk.UserTranslationId;
import com.vitgon.schedule.projection.UserProjection;
import com.vitgon.schedule.service.database.base.Service;

import java.util.Optional;

public interface UserTranslationService extends Service<UserTranslation, UserTranslationId> {
	UserTranslation findByLocaleAndUser(Locale locale, User user);
	Optional<UserTranslation> findByLocaleIdAndUserId(Integer localeId, Integer userId);
	UserProjection getUserTranslationProjectionByLocaleIdAndUserId(Integer localeId, Integer userId);
}
