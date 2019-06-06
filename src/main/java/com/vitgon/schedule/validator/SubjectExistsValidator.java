package com.vitgon.schedule.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.vitgon.schedule.annotation.validation.SubjectExists;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.service.database.SubjectService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class SubjectExistsValidator implements ConstraintValidator<SubjectExists, Integer> {
	
	private SubjectService subjectService;

	@Override
	public boolean isValid(Integer id, ConstraintValidatorContext context) {
		Optional<Subject> subject = subjectService.findById(id);
		if (subject.isPresent()) {
			return true;
		}
		return false;
	}

}
