package com.vitgon.schedule.service;

import java.util.List;
import java.util.Map;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.School;
import com.vitgon.schedule.service.base.Service;

public interface SchoolService extends Service<School, Integer> {
	School findByTitle(String title);
	Map<Integer, Map<String, Object>> findAllByLocale(Locale locale);
}
