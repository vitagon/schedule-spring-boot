package com.vitgon.schedule.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.vitgon.schedule.annotation.validation.SchoolExists;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.service.database.SchoolService;

import lombok.AllArgsConstructor;

@Component
public class SchoolExistsValidator implements ConstraintValidator<SchoolExists, Integer> {
	
	private SchoolService schoolService;

	public SchoolExistsValidator(SchoolService schoolService) {
		this.schoolService = schoolService;
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		Optional<School> schoolOpt = schoolService.findById(value);
		if (schoolOpt.isPresent()) {
			return true;
		}
		return false;
	}
}
