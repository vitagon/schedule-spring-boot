package com.vitgon.schedule.service;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.service.database.LocaleService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Service
public class LocaleConverterService {
	
	private LocaleService localeService;

	public LocaleConverterService(LocaleService localeService) {
		this.localeService = localeService;
	}

	public Locale getClientLocale(HttpServletRequest request) {
		java.util.Locale loc = (java.util.Locale) request.getSession().getAttribute("URL_LOCALE_ATTRIBUTE_NAME");
		return localeService.findByCode(loc.getLanguage());
	}
	
	public Locale getClientLocale() {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
	    ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
	    HttpServletRequest request = attributes.getRequest();
	    java.util.Locale loc = (java.util.Locale) request.getSession().getAttribute("URL_LOCALE_ATTRIBUTE_NAME");
		return localeService.findByCode(loc.getLanguage());
	}
}
