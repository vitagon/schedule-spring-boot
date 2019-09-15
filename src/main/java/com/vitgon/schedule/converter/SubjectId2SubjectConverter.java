package com.vitgon.schedule.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.service.database.SubjectService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
public class SubjectId2SubjectConverter implements Converter<Integer, Subject> {

	private SubjectService subjectService;

	public SubjectId2SubjectConverter() {
	}

	@Autowired
	public SubjectId2SubjectConverter(SubjectService subjectService) {
		this.subjectService = subjectService;
	}

	@Override
	public Subject convert(Integer subjectId) {
		if (subjectId == 0 || subjectId < 0) {
			throw new IllegalArgumentException("Subject id must be greater than 0!");
		}
		return subjectService.findById(subjectId).get();
	}
}
