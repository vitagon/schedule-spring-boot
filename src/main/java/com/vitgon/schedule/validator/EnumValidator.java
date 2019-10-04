package com.vitgon.schedule.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.vitgon.schedule.annotation.validation.EnumMatch;

@Component
public class EnumValidator implements ConstraintValidator<EnumMatch, String> {
	
	private List<String> enumElementsList = new ArrayList<>();

	@SuppressWarnings("rawtypes")
	@Override
	public void initialize(EnumMatch constraintAnnotation) {
		Class<? extends Enum> enumClazz = constraintAnnotation.enumClazz();
		Enum[] enumConstants = enumClazz.getEnumConstants();
		for (Enum enumConstant : enumConstants) {
			enumElementsList.add(enumConstant.toString().toUpperCase());
		}
	}

	@Override
	public boolean isValid(String enumElement, ConstraintValidatorContext context) {
		if (enumElementsList.contains(enumElement)) {
			return true;
		}
		return false;
	}
}
