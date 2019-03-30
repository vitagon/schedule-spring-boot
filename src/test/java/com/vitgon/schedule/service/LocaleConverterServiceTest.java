package com.vitgon.schedule.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.MessageSource;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.service.database.LocaleService;

public class LocaleConverterServiceTest {

	private LocaleService localeService;
	private LocaleConverterService localeConverterService;
	
	@Before
	public void init() {
		localeService = mock(LocaleService.class);
		localeConverterService = new LocaleConverterService(localeService);
	}
	
	@Test
	public void testgetClientLocaleMethodWithRequest() {
		java.util.Locale javaLocale = new java.util.Locale("en");
		Locale locale = new Locale("en");
		HttpServletRequest request = spy(HttpServletRequest.class);
		HttpSession session = spy(HttpSession.class);
		
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("URL_LOCALE_ATTRIBUTE_NAME")).thenReturn(javaLocale);
		when(localeService.findByCode(javaLocale.getLanguage())).thenReturn(locale);
		
		Locale clientLocale = localeConverterService.getClientLocale(request);
		Assert.assertEquals(locale, clientLocale);
		Assert.assertEquals(javaLocale.getLanguage(), clientLocale.getCode());
		verify(localeService).findByCode(javaLocale.getLanguage());
	}
}
