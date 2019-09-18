package com.vitgon.schedule.service.database;

import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.projection.MajorProjection;
import com.vitgon.schedule.service.database.base.Service;

import java.util.List;
import java.util.Optional;

public interface MajorService extends Service<Major, Integer>{
	Major findByTranslation(String translation);
	Optional<Major> findByUrl(String url);
	Optional<Major> findByName(String name);
	List<MajorProjection> findBySchoolIdAndLocaleId(Integer schoolId, Integer localeId);
	List<MajorProjection> findBySchoolIdAndBrowserDefaultLanguage(Integer schoolId);
	List<MajorProjection> getAllLeftJoiningOnLocaleId(Integer localeId);

    Optional<MajorProjection> getByLocaleIdAndMajorId(Integer id, Integer majorId);
}
