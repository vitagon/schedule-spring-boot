package com.vitgon.schedule.validator;

import com.vitgon.schedule.annotation.validation.SchoolExists;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.service.database.SchoolService;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Component
public class SchoolExistsValidator implements ConstraintValidator<SchoolExists, Integer> {
	
	private SchoolService schoolService;

	public SchoolExistsValidator(SchoolService schoolService) {
		this.schoolService = schoolService;
	}

	@Override
	public boolean isValid(Integer id, ConstraintValidatorContext context) {
		Optional<School> schoolOpt = schoolService.findById(id);
		if (schoolOpt.isPresent()) {
			return true;
		}
		return false;
	}
}
