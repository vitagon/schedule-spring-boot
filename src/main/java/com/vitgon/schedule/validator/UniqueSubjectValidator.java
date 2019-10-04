package com.vitgon.schedule.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.vitgon.schedule.annotation.validation.UniqueSubject;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.service.database.SubjectService;

@Component
public class UniqueSubjectValidator implements ConstraintValidator<UniqueSubject, String> {
	
	private SubjectService subjectService;

	public UniqueSubjectValidator(SubjectService subjectService) {
		super();
		this.subjectService = subjectService;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Subject subject = subjectService.findByName(value.toLowerCase());
		if (subject == null) {
			return true;
		}
		return false;
	}
}
