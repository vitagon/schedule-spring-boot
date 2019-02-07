package com.vitgon.schedule.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vitgon.schedule.dto.SubjectDTO;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Subject;
import com.vitgon.schedule.service.SubjectMapperService;
import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.service.database.SubjectService;

@RestController
@RequestMapping("/api")
public class SubjectRestController {
	
	private SubjectService subjectService;
	private LocaleService localeService;
	private SubjectMapperService subjectMapperService;
	
	@Autowired
	public SubjectRestController(SubjectService subjectService,
								 LocaleService localeService,
								 SubjectMapperService subjectMapperService) {
		this.subjectService = subjectService;
		this.localeService = localeService;
		this.subjectMapperService = subjectMapperService;
	}
	
	@GetMapping("/subjects")
	public List<SubjectDTO> getListOfSubject(@RequestParam String localeCode) {
		Locale locale = localeService.findByCode(localeCode);
		if (locale == null) {
			throw new IllegalArgumentException("Provided localeCode doesn't exist");
		}
		List<Subject> subjects = subjectService.findAll();
		return subjectMapperService.mapToSubjectDTOList(subjects, locale);
	}
}
