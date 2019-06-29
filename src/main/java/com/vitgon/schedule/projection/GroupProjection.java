package com.vitgon.schedule.projection;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vitgon.schedule.converter.DegreeEnumToStringConverter;
import com.vitgon.schedule.dto.DegreeEnum;

public interface GroupProjection {
	Integer getId();
	Integer getNumber();
	String getSuffix();
	Integer getCourse_num();
	String getSuffix_translation();
	
	@JsonSerialize(converter = DegreeEnumToStringConverter.class)
	DegreeEnum getDegree();
}
