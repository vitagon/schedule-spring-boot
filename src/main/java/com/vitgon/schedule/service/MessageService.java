package com.vitgon.schedule.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Service
public class MessageService {
	
	private MessageSource messageSource;

	public MessageService(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public String getMessage(String i18nCode) {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
	    ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
	    HttpServletRequest request = attributes.getRequest();
		Locale locale = (java.util.Locale) request.getSession().getAttribute("URL_LOCALE_ATTRIBUTE_NAME");
		return messageSource.getMessage(i18nCode, null, locale);
	}
}
