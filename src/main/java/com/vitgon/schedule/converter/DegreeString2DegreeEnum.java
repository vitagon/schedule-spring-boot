package com.vitgon.schedule.converter;

import org.springframework.core.convert.converter.Converter;

import com.vitgon.schedule.dto.DegreeEnum;

public class DegreeString2DegreeEnum implements Converter<String, DegreeEnum> {

	@Override
	public DegreeEnum convert(String source) {
		DegreeEnum degree;
		try {
			degree = DegreeEnum.valueOf(source.toUpperCase());
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Degree enum constant was not found!");
		}
		return degree;
	}
}
