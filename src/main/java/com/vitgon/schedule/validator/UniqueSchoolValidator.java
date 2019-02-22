package com.vitgon.schedule.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.vitgon.schedule.annotation.validation.UniqueSchool;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.service.database.SchoolService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UniqueSchoolValidator implements ConstraintValidator<UniqueSchool, String>{
	
	private SchoolService schoolService;

	@Override
	public boolean isValid(String schoolName, ConstraintValidatorContext context) {
		School school = schoolService.findByName(schoolName);
		if (school == null) {
			return true;
		}
		return false;
	}
}
