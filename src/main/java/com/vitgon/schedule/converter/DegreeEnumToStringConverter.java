package com.vitgon.schedule.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.vitgon.schedule.dto.DegreeEnum;
import com.vitgon.schedule.service.MessageService;

@Component
public class DegreeEnumToStringConverter extends StdConverter<DegreeEnum, String> {
	
	private final static Logger log = LoggerFactory.getLogger(DegreeEnumToStringConverter.class);
	private MessageService messageService;

	public DegreeEnumToStringConverter(MessageService messageService) {
		this.messageService = messageService;
	}

	@Override
	public String convert(DegreeEnum source) {
		log.info(source.name().toLowerCase());
		return messageService.getMessage(source.name().toLowerCase());
	}
}
