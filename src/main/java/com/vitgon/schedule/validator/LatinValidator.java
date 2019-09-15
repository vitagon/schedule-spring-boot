package com.vitgon.schedule.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.vitgon.schedule.annotation.validation.Latin;

import lombok.AllArgsConstructor;

public class LatinValidator implements ConstraintValidator<Latin, String> {

	public LatinValidator() {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value.matches("[A-Za-z -]*")) {
			return true;
		}
		return false;
	}
}
