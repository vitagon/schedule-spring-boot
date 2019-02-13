package com.vitgon.schedule.service.database;

import java.util.List;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.service.database.base.Service;

public interface SchoolService extends Service<School, Integer> {
	School findByTitle(String title);
	List<School> findAllByLocale(Locale locale);
}
