package com.vitgon.schedule.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.service.LocaleService;

@RestController
@RequestMapping("/api")
public class LocaleController {
	private static final Logger logger = LoggerFactory.getLogger(LocaleController.class);
	
	@Autowired
	private LocaleService localeService;
	
	@GetMapping("/create")
	public void createEvent() {
		
		Locale locale = new Locale("english", "en_US");
		logger.info("Creating locale ... {}", locale);
		localeService.save(locale);
	}
	
	@GetMapping("/findall")
	public List<Locale> findAll() {
		return localeService.findAll();
	}
}
