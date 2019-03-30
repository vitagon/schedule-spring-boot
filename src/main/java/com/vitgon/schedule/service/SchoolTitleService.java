package com.vitgon.schedule.service;

import org.springframework.stereotype.Service;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.model.database.translation.SchoolTranslation;
import com.vitgon.schedule.resolver.UrlLocaleResolver;
import com.vitgon.schedule.service.database.translation.SchoolTranslationService;
import com.vitgon.schedule.util.StringUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SchoolTitleService {
	
	private SchoolTranslationService schoolTranslationService;

	public String getSchoolTitle(Locale locale, School school) {
		if (locale.getCode().equals(UrlLocaleResolver.EN)) {
			return StringUtil.capitalizeFirstLetter(school.getName());
		}
		
		SchoolTranslation schoolTranslation = schoolTranslationService.findByLocaleAndSchool(locale, school);
		
		if (schoolTranslation != null) {
			return StringUtil.capitalizeFirstLetter(schoolTranslation.getTitle());
		} else {
			return StringUtil.capitalizeFirstLetter(school.getName());
		}
	}
}
