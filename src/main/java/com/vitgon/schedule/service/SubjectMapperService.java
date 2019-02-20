package com.vitgon.schedule.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	
	/**
	 * Map list of subjects to list of subject dto
	 * 
	 * @param subjects
	 * @param locale
	 * @param substituteNull if translation doesn't exist, then take english name
	 * @return
	 */
	public List<SubjectDTO> mapToSubjectDTOList(List<Subject> subjects, Locale locale, boolean substituteNull) {
		return subjects.stream()
				.map(subject -> {
					String translation = subjectTranslService.getSubjectTitle(subject, locale, substituteNull);
					return new SubjectDTO(subject.getId(), subject.getName(), translation);
				})
				.collect(Collectors.toList());
	}
	
	/**
	 * 
	 * @param locale
	 * @param substituteNull
	 * @return
	 */
	public Map<Integer, String> mapToMap(Locale locale, boolean substituteNull) {
		List<Subject> subjects = subjectService.findAll();
		Map<Integer, String> subjectTitles = new HashMap<>();
		for (Subject subject : subjects) {
			subjectTitles.put(subject.getId(), subjectTranslService.getSubjectTitle(subject, locale, substituteNull));
		}
		return subjectTitles;
	}
}
