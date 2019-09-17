package com.vitgon.schedule.converter;

import com.vitgon.schedule.dto.Days;
import org.springframework.core.convert.converter.Converter;

public class StringToDaysEnumConverter implements Converter<String, Days> {

	@Override
	public Days convert(String source) {
		return Days.valueOf(source.toUpperCase());
	}
}
