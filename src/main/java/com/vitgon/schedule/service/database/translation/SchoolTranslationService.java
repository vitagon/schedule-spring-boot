package com.vitgon.schedule.service.database.translation;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.model.database.translation.SchoolTranslation;
import com.vitgon.schedule.model.database.translation.pk.SchoolTranslationId;
import com.vitgon.schedule.service.database.base.Service;

public interface SchoolTranslationService extends Service<SchoolTranslation, SchoolTranslationId> {
	SchoolTranslation findByLocaleAndSchool(Locale locale, School school);
}
