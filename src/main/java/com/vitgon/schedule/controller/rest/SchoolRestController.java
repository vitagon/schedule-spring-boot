package com.vitgon.schedule.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitgon.schedule.dao.SchoolDao;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.School;
import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.service.database.SchoolService;

@RestController
@RequestMapping("/api")
public class SchoolRestController {
	
	private SchoolService schoolService;
	private LocaleService localeService;
	
	public SchoolRestController() {
	}

	@Autowired
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
