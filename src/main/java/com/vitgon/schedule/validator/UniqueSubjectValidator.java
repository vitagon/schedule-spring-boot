package com.vitgon.schedule.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.vitgon.schedule.annotation.UniqueSubject;

public class UniqueSubjectValidator implements ConstraintValidator<UniqueSubject, String> {
	
	

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value.equals("Петров")) {
			return false;
		}
		return true;
	}
}
