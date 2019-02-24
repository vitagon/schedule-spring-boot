package com.vitgon.schedule.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.vitgon.schedule.annotation.validation.EnumMatch;

public class EnumValidator implements ConstraintValidator<EnumMatch, String> {
	
	private List<String> valueList = new ArrayList<>();

	@Override
	public void initialize(EnumMatch constraintAnnotation) {
		Class<? extends Enum> enumClazz = constraintAnnotation.enumClazz();
		Enum[] enumConstants = enumClazz.getEnumConstants();
		for (Enum enumConstant : enumConstants) {
			valueList.add(enumConstant.toString().toUpperCase());
		}
	}

	@Override
	public boolean isValid(String degreeType, ConstraintValidatorContext context) {
		if (valueList.contains(degreeType)) {
			return true;
		}
		return false;
	}
}
