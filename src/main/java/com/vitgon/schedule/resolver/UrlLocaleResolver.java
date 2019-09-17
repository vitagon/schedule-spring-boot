package com.vitgon.schedule.resolver;

import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

public class UrlLocaleResolver implements LocaleResolver {
	
	private static final String COOKIE_LOCALE_ATTRIBUTE_NAME = "user_lang";
	private static final Set<String> supportedLocales = new HashSet<>();
	
	public static final String EN = "en";
	public static final String RU = "ru";
	
	public static final String URL_LOCALE_ATTRIBUTE_NAME = "URL_LOCALE_ATTRIBUTE_NAME";
	
	{
		supportedLocales.add(EN);
		supportedLocales.add(RU);
	}
	
	

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		
		Locale localePreferred = request.getLocale();
		Cookie[] cookies = request.getCookies();
		String lang = null;
		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (COOKIE_LOCALE_ATTRIBUTE_NAME.equals(cookie.getName())) {
					lang = cookie.getValue();
				}
			}
		}
		
//		System.out.println("Locale ::::: " + localePreferred.getLanguage());
//		System.out.println("Lang in cookies :::::: " + lang);
		
		Locale locale = null;
		
		if (lang != null && !lang.equals("")) {
			switch (lang) {
				case EN: {
					locale = Locale.ENGLISH;
					break;
				}
				case RU: {
					locale = new Locale(RU);
					break;
				}
				default: {
					locale = Locale.ENGLISH;
					break;
				}
			}
		}
		
		if (lang == null || lang.equals("")) {
			if (localePreferred != null && localePreferred.getLanguage() != null) {
				Optional<String> isSupported = supportedLocales.stream()
					.filter(localeLang -> {
						String clientLang = localePreferred.getLanguage();
						String supportedLang = new Locale(localeLang).getLanguage();
						return 	clientLang.equals(supportedLang);
					}).findFirst();
				
				if (isSupported.isPresent()) {
					locale = localePreferred;
				} else {
					locale = Locale.ENGLISH;
				}
			}
		}
		
		if (locale == null) {
			locale = Locale.ENGLISH;
		}
		
		request.getSession().setAttribute(URL_LOCALE_ATTRIBUTE_NAME, locale);
		
		return locale;
	}

	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
	}
}
