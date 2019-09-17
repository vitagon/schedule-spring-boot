package com.vitgon.schedule.controller.rest;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.service.database.SchoolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SchoolRestController {
	
	private SchoolService schoolService;
	private LocaleService localeService;

	public SchoolRestController(SchoolService schoolService, LocaleService localeService) {
		this.schoolService = schoolService;
		this.localeService = localeService;
	}

	@GetMapping("/schools")
	public List<School> getSchools() {
		Locale locale = localeService.findByCode("en");
		return schoolService.findAllByLocale(locale);
	}
}
