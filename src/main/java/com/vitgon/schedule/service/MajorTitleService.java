package com.vitgon.schedule.service;

import org.springframework.stereotype.Service;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.model.database.translation.MajorTranslation;
import com.vitgon.schedule.resolver.UrlLocaleResolver;
import com.vitgon.schedule.service.database.translation.MajorTranslationService;
import com.vitgon.schedule.util.StringUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MajorTitleService {
	
	private MajorTranslationService majorTranslationService;
	
	public String getMajorTitle(Locale locale, Major major) {
		if (locale.getCode().equals(UrlLocaleResolver.EN)) {
			return major.getName();
		}
		
		MajorTranslation majorTranslation = majorTranslationService.findByLocaleAndMajor(locale, major);
		if (majorTranslation != null) {
			return StringUtil.capitalizeFirstLetter(majorTranslation.getTitle());
		}
		return null;
	}
}
