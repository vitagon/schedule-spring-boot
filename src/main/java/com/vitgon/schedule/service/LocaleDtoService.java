package com.vitgon.schedule.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vitgon.schedule.dto.LocaleDto;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.service.database.LocaleService;

import lombok.AllArgsConstructor;

@Service
public class LocaleDtoService {
	
	private LocaleService localeService;
	private LocaleConverterService localeConverterService;

	public LocaleDtoService(LocaleService localeService, LocaleConverterService localeConverterService) {
		this.localeService = localeService;
		this.localeConverterService = localeConverterService;
	}

	public List<LocaleDto> getLocaleDtoList() {
		Locale clientLocale = localeConverterService.getClientLocale();
		List<Locale> locales = localeService.findAll();
		return locales.stream()
			.map(locale -> {
				LocaleDto localeDto = new LocaleDto();
				localeDto.setId(locale.getId());
				localeDto.setCode(locale.getCode());
				
				if (clientLocale.getId() == locale.getId()) {
					localeDto.setActive(true);
				}
				return localeDto;
			})
			.collect(Collectors.toList());
	}
}
