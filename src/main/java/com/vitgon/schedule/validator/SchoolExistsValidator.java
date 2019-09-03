package com.vitgon.schedule.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.vitgon.schedule.annotation.validation.SchoolExists;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.service.database.SchoolService;

public class SchoolExistsValidator implements ConstraintValidator<SchoolExists, Integer> {
	
	private SchoolService schoolService;

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		Optional<School> schoolOpt = schoolService.findById(value);
		if (schoolOpt.isPresent()) {
			return true;
		}
		return false;
	}
}
