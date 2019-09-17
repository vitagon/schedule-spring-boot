package com.vitgon.schedule.controller.rest;

import com.vitgon.schedule.dto.LocaleDto;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.service.LocaleDtoService;
import com.vitgon.schedule.service.database.LocaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LocaleRestController {
	private static final Logger logger = LoggerFactory.getLogger(LocaleRestController.class);
	
	private LocaleService localeService;
	private LocaleDtoService localeMapperService;

	public LocaleRestController(LocaleService localeService, LocaleDtoService localeMapperService) {
		this.localeService = localeService;
		this.localeMapperService = localeMapperService;
	}

	@GetMapping("/create")
	public void createEvent() {
		
		Locale locale = new Locale("en");
		logger.info("Creating locale ... {}", locale);
		localeService.save(locale);
	}
	
	@GetMapping("/locales")
	public List<LocaleDto> findAll() {
		return localeMapperService.getLocaleDtoList();
	}
}
