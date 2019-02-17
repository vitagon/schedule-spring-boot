package com.vitgon.schedule.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.vitgon.schedule.dto.SubjectDTO;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.service.database.SubjectService;
import com.vitgon.schedule.service.database.translation.SubjectTranslationService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class SubjectMapperService {
	
	private SubjectService subjectService;
	private SubjectTranslationService subjectTranslService;
	
	public List<SubjectDTO> mapToSubjectDTOList() {
		List<Subject> subjects = subjectService.findAll();
		return mapToSubjectDTOList(subjects);
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
