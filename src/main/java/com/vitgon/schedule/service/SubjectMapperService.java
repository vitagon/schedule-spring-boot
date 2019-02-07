package com.vitgon.schedule.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vitgon.schedule.dto.SubjectDTO;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Subject;
import com.vitgon.schedule.service.database.translation.SubjectTranslationService;

@Component
public class SubjectMapperService {
	
	private SubjectTranslationService subjectTranslService;

	@Autowired
	public SubjectMapperService(SubjectTranslationService subjectTranslService) {
		this.subjectTranslService = subjectTranslService;
	}

	public List<SubjectDTO> mapToSubjectDTOList(List<Subject> subjects) {
		return subjects.stream()
				.map(subject -> {
					return new SubjectDTO(subject.getId(), subject.getName());
				})
				.collect(Collectors.toList());
	}
	
	public List<SubjectDTO> mapToSubjectDTOList(List<Subject> subjects, Locale locale) {
		return subjects.stream()
				.map(subject -> {
					return new SubjectDTO(subject.getId(), subject.getName(), subjectTranslService.getSubjectTitle(subject, locale));
				})
				.collect(Collectors.toList());
	}
}
