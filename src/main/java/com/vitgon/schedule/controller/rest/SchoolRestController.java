package com.vitgon.schedule.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitgon.schedule.dao.SchoolDao;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.School;
import com.vitgon.schedule.service.LocaleService;
import com.vitgon.schedule.service.SchoolService;

@RestController
@RequestMapping("/api")
public class SchoolRestController {
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private LocaleService localeService;
	
	@GetMapping("/schools")
	public List<School> getSchools() {
		Locale locale = localeService.findByCode("en");
		return schoolService.findAllByLocale(locale);
	}
}
