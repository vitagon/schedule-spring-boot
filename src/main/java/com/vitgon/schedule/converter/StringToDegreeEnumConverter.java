package com.vitgon.schedule.converter;

import org.springframework.core.convert.converter.Converter;

import com.vitgon.schedule.dto.DegreeEnum;

public class StringToDegreeEnumConverter implements Converter<String, DegreeEnum> {
	
	@Override
	public DegreeEnum convert(String source) {
		return DegreeEnum.valueOf(source);
	}
}
