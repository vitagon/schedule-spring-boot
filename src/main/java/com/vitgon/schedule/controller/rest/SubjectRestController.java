package com.vitgon.schedule.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.vitgon.schedule.annotation.validation.LocaleExists;
import com.vitgon.schedule.dto.SubjectDto;
import com.vitgon.schedule.service.SubjectDtoService;
import com.vitgon.schedule.view.Views;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SubjectRestController {
	
	private SubjectDtoService subjectDtoService;

	public SubjectRestController(SubjectDtoService subjectDtoService) {
		this.subjectDtoService = subjectDtoService;
	}

	@GetMapping("/subjects")
	public List<SubjectDto> getSubjectsByLocale() {
		return subjectDtoService.getSubjectDtoList();
	}
	
	@GetMapping("/subjects/locale-id/{localeId}")
	@JsonView(Views.Public.class)
	public List<SubjectDto> getSubjectDtoListByLocale(@PathVariable("localeId") @LocaleExists Integer localeId) {
		return subjectDtoService.getSubjectDtoListByLocaleId(localeId);
	}
}
