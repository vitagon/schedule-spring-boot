package com.vitgon.schedule.service.database.translation;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.model.database.translation.MajorTranslation;
import com.vitgon.schedule.model.database.translation.pk.MajorTranslationId;
import com.vitgon.schedule.service.database.base.Service;

public interface MajorTranslationService extends Service<MajorTranslation, MajorTranslationId> {
	MajorTranslation findByLocaleAndMajor(Locale locale, Major major);
}
