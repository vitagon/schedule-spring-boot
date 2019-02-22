package com.vitgon.schedule.converter;

import org.springframework.core.convert.converter.Converter;

import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.service.database.SchoolService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SchoolId2SchoolConverter implements Converter<Integer, School> {

	private SchoolService schoolService;

	@Override
	public School convert(Integer schoolId) {
		if (schoolId == 0 || schoolId < 0) {
			throw new IllegalArgumentException("School id must be equal or greater than 0!");
		}
		School school = schoolService.findById(schoolId);
		return school;
	}
}
