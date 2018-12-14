package com.vitgon.schedule.resolver;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.LocaleResolver;

public class UrlLocaleResolver implements LocaleResolver {
	
	private static final String URL_LOCALE_ATTRIBUTE_NAME = "URL_LOCALE_ATTRIBUTE_NAME";

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		String uri = request.getRequestURI();
		
		String prefixEn = request.getServletContext().getContextPath() + "/en/";
		String prefixRu = request.getServletContext().getContextPath() + "/ru/";
		
		Locale locale = null;
		
		if (uri.startsWith(prefixEn)) {
			locale = Locale.ENGLISH;
		}
		else if(uri.startsWith(prefixRu)) {
			locale = new Locale("ru");
		}
		
		if (locale != null) {
			request.getSession().setAttribute(URL_LOCALE_ATTRIBUTE_NAME, locale);
		}
		
		if (locale == null) {
			locale = (Locale) request.getSession().getAttribute(URL_LOCALE_ATTRIBUTE_NAME);
			if (locale == null) {
				locale = Locale.ENGLISH;
			}
		}
		
		return locale;
	}

	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		
	}
}
