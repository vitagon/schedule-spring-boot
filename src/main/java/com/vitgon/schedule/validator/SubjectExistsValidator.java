package com.vitgon.schedule.validator;

import com.vitgon.schedule.annotation.validation.SubjectExists;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.service.database.SubjectService;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Component
public class SubjectExistsValidator implements ConstraintValidator<SubjectExists, Integer> {
	
	private SubjectService subjectService;

	public SubjectExistsValidator(SubjectService subjectService) {
		this.subjectService = subjectService;
	}

	@Override
	public boolean isValid(Integer id, ConstraintValidatorContext context) {
		Optional<Subject> subject = subjectService.findById(id);
		if (subject.isPresent()) {
			return true;
		}
		return false;
	}

}
