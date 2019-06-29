package com.vitgon.schedule.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vitgon.schedule.dto.LocaleDto;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.service.database.LocaleService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LocaleDtoService {
	
	private LocaleService localeService;

	public List<LocaleDto> getLocaleDtoList() {
		List<Locale> locales = localeService.findAll();
		return locales.stream()
			.map(locale -> {
				LocaleDto localeDto = new LocaleDto();
				localeDto.setId(locale.getId());
				localeDto.setCode(locale.getCode());
				return localeDto;
			})
			.collect(Collectors.toList());
	}
}
