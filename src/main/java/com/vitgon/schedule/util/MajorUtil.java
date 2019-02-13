package com.vitgon.schedule.util;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.model.database.translation.MajorTranslation;

public class MajorUtil {
	
	public static String getMajorTitle(Major major, Locale locale) {
		if (locale.getCode().equals("en")) {
			return major.getName();
		}
		
		return major.getTranslations().stream()
			.filter(x -> locale == x.getLocale())
			.map(MajorTranslation::getTitle)
			.findFirst().get();
	}
}
