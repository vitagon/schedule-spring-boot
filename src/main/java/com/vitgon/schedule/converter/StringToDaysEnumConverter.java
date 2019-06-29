package com.vitgon.schedule.converter;

import org.springframework.core.convert.converter.Converter;

import com.vitgon.schedule.dto.Days;

public class StringToDaysEnumConverter implements Converter<String, Days> {

	@Override
	public Days convert(String source) {
		return Days.valueOf(source.toUpperCase());
	}
}
