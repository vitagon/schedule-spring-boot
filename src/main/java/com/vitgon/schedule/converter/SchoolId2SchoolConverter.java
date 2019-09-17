package com.vitgon.schedule.converter;

import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.service.database.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.Optional;


public class SchoolId2SchoolConverter implements Converter<Integer, School> {

	private SchoolService schoolService;

	public SchoolId2SchoolConverter() {
	}

	@Autowired
	public SchoolId2SchoolConverter(SchoolService schoolService) {
		this.schoolService = schoolService;
	}

	@Override
	public School convert(Integer schoolId) {
		if (schoolId == 0 || schoolId < 0) {
			throw new IllegalArgumentException("School id must be equal or greater than 0!");
		}
		Optional<School> school = schoolService.findById(schoolId);
		return school.get();
	}
}
