package com.vitgon.schedule.service.database.translation;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.model.database.translation.MajorTranslation;
import com.vitgon.schedule.model.database.translation.pk.MajorTranslationId;
import com.vitgon.schedule.service.database.base.Service;

import java.util.Optional;

public interface MajorTranslationService extends Service<MajorTranslation, MajorTranslationId> {
    Optional<MajorTranslation> findByLocaleIdAndMajorId(Integer localeId, Integer majorId);

    void save(Integer majorId, Integer localeId, String translation);
    void deleteByMajorIdAndLocaleId(Integer majorId, Integer localeId);
}
