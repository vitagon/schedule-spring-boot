package com.vitgon.schedule.validator;

import com.vitgon.schedule.annotation.validation.UniqueMajor;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.service.database.MajorService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueMajorValidator implements ConstraintValidator<UniqueMajor, String> {

	private MajorService majorService;

	public UniqueMajorValidator(MajorService majorService) {
		this.majorService = majorService;
	}

	@Override
	public boolean isValid(String majorName, ConstraintValidatorContext context) {
		Optional<Major> major = majorService.findByName(majorName);
		if (!major.isPresent()) {
			return true;
		}
		return false;
	}
}
