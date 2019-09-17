package com.vitgon.schedule.validator;

import com.vitgon.schedule.annotation.validation.UniqueSchool;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.service.database.SchoolService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueSchoolValidator implements ConstraintValidator<UniqueSchool, String>{
	
	private SchoolService schoolService;

	public UniqueSchoolValidator(SchoolService schoolService) {
		this.schoolService = schoolService;
	}

	@Override
	public boolean isValid(String schoolName, ConstraintValidatorContext context) {
		School school = schoolService.findByName(schoolName);
		if (school == null) {
			return true;
		}
		return false;
	}
}
