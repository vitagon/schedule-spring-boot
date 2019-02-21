package com.vitgon.schedule.converter;

import org.springframework.core.convert.converter.Converter;

import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.service.database.SubjectService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SubjectId2SubjectConverter implements Converter<Integer, Subject> {
	
	private SubjectService subjectService;

	@Override
	public Subject convert(Integer subjectId) {
		try {
			Integer id = Integer.valueOf(subjectId);
			if (subjectId == 0 || subjectId < 0) {
				throw new IllegalArgumentException("Subject id must be greater than 0!");
			}
			return subjectService.findById(id);
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
