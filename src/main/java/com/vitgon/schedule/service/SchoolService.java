package com.vitgon.schedule.service;

import java.util.List;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.School;
import com.vitgon.schedule.service.base.Service;

public interface SchoolService extends Service<School, Integer> {
	School findByTitle(String title);
	List<School> findAllByLocale(Locale locale);
}
