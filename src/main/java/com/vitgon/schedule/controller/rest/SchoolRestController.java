package com.vitgon.schedule.controller.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.service.database.SchoolService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class SchoolRestController {
	
	private SchoolService schoolService;
	private LocaleService localeService;

	@GetMapping("/schools")
	public List<School> getSchools() {
		Locale locale = localeService.findByCode("en");
		return schoolService.findAllByLocale(locale);
	}
}
