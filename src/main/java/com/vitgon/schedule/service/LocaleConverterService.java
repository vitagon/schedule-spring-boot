package com.vitgon.schedule.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.service.database.LocaleService;

@Service
public class LocaleConverterService {
	
	private LocaleService localeService;

	@Autowired
	public LocaleConverterService(LocaleService localeService) {
		this.localeService = localeService;
	}
	
	public Locale getClientLocale(HttpServletRequest request) {
		java.util.Locale loc = (java.util.Locale) request.getSession().getAttribute("URL_LOCALE_ATTRIBUTE_NAME");
		return localeService.findByCode(loc.getLanguage());
	}
}
