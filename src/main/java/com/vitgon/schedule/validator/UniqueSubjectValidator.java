package com.vitgon.schedule.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.vitgon.schedule.annotation.UniqueSubject;
import com.vitgon.schedule.model.Subject;
import com.vitgon.schedule.service.database.SubjectService;

public class UniqueSubjectValidator implements ConstraintValidator<UniqueSubject, String> {
	
	private SubjectService subjectService;
	
	public UniqueSubjectValidator(SubjectService subjectService) {
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
