package com.vitgon.schedule.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.vitgon.schedule.annotation.validation.UniqueMajor;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.service.database.MajorService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UniqueMajorValidator implements ConstraintValidator<UniqueMajor, String> {

	private MajorService majorService;
	
	@Override
	public boolean isValid(String majorName, ConstraintValidatorContext context) {
		Major major = majorService.findByName(majorName);
		if (major == null) {
			return true;
		}
		return false;
	}
}
