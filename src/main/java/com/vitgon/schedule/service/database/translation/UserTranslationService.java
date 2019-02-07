package com.vitgon.schedule.service.database.translation;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.auth.User;
import com.vitgon.schedule.model.translation.UserTranslation;
import com.vitgon.schedule.model.translation.pk.UserTranslationId;
import com.vitgon.schedule.service.database.base.Service;

public interface UserTranslationService extends Service<UserTranslation, UserTranslationId> {

	UserTranslation findByLocaleAndUser(Locale locale, User user);
}
