package com.vitgon.schedule.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.service.database.LocaleService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LocaleConverterService {
	
	private LocaleService localeService;
	
	public Locale getClientLocale(HttpServletRequest request) {
		java.util.Locale loc = (java.util.Locale) request.getSession().getAttribute("URL_LOCALE_ATTRIBUTE_NAME");
		return localeService.findByCode(loc.getLanguage());
	}
}
