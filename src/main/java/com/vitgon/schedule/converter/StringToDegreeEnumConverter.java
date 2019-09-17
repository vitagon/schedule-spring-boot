package com.vitgon.schedule.converter;

import com.vitgon.schedule.dto.DegreeEnum;
import org.springframework.core.convert.converter.Converter;

public class StringToDegreeEnumConverter implements Converter<String, DegreeEnum> {
	
	@Override
	public DegreeEnum convert(String source) {
		return DegreeEnum.valueOf(source);
	}
}
