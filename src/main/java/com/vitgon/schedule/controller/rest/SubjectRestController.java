package com.vitgon.schedule.controller.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.vitgon.schedule.dto.SubjectDTO;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.service.SubjectMapperService;
import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.service.database.SubjectService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class SubjectRestController {
	
	private SubjectService subjectService;
	private LocaleService localeService;
	private SubjectMapperService subjectMapperService;
	
	@GetMapping("/subjects")
	public List<SubjectDTO> getSubjectsByLocale() {
		List<Subject> subjects = subjectService.findAll();
		return subjectMapperService.mapToSubjectDTOList(subjects);
	}
	
	@GetMapping("/subjects/locale")
	public List<SubjectDTO> getSubjectDTOListByLocale(@RequestParam int localeId) {
		if (localeId < 0) {
			throw new IllegalArgumentException("Locale id must be equal OR greater than 0!");
		}
		if (localeId == 0) {
			List<Subject> subjects = subjectService.findAll();
			return subjectMapperService.mapToSubjectDTOList(subjects);
		}
		
		Locale locale = localeService.findById(localeId);
		if (locale == null) {
			throw new IllegalArgumentException("Provided localeCode doesn't exist");
		}
		List<Subject> subjects = subjectService.findAll();
		return subjectMapperService.mapToSubjectDTOList(subjects, locale, false);
	}
}
