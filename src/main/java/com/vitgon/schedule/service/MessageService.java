package com.vitgon.schedule.service;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MessageService {
	
	private MessageSource messageSource;

	public String getMessage(String code, HttpServletRequest request) {
		Locale locale = (java.util.Locale) request.getSession().getAttribute("URL_LOCALE_ATTRIBUTE_NAME");
		return messageSource.getMessage(code, null, locale);
	}
}
