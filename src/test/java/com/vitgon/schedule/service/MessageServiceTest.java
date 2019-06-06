package com.vitgon.schedule.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.MessageSource;

public class MessageServiceTest {

	private MessageSource messageSource;
	private MessageService messageService;
	
	@Before
	public void init() {
		messageSource = mock(MessageSource.class);
		messageService = new MessageService(messageSource);
	}
	
//	@Test
//	public void testGetMessageMethodWithCodeAndRequest() {
//		Locale locale = new Locale("en");
//		String code = "login";
//		HttpServletRequest request = spy(HttpServletRequest.class);
//		HttpSession session = spy(HttpSession.class);
//		
//		when(request.getSession()).thenReturn(session);
//		when(session.getAttribute("URL_LOCALE_ATTRIBUTE_NAME")).thenReturn(locale);
//		when(messageSource.getMessage(code, null, locale)).thenReturn("логин");
//		
//		String messageTranslation = messageService.getMessage(code);
//		Assert.assertEquals("логин", messageTranslation);
//		verify(messageSource).getMessage(code, null, locale);
//	}
}
