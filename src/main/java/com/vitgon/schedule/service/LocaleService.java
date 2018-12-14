package com.vitgon.schedule.service;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.service.base.Service;

public interface LocaleService extends Service<Locale, Integer> {
	Locale findByCode(String code);
	Locale get(String lang);
}
