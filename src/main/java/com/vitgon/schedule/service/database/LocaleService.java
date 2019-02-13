package com.vitgon.schedule.service.database;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.service.database.base.Service;

public interface LocaleService extends Service<Locale, Integer> {
	Locale findByCode(String code);
}
