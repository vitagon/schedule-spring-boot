package com.vitgon.schedule.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.service.database.SchoolService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class SchoolId2SchoolConverter implements Converter<Integer, School> {

	@Autowired
	private SchoolService schoolService;

	@Override
	public School convert(Integer schoolId) {
		if (schoolId == 0 || schoolId < 0) {
			throw new IllegalArgumentException("School id must be equal or greater than 0!");
		}
		Optional<School> school = schoolService.findById(schoolId);
		return school.get();
	}
}
