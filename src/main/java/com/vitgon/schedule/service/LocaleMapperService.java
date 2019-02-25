package com.vitgon.schedule.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vitgon.schedule.dto.LocaleDto;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.util.LocaleUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LocaleMapperService {
	
	private LocaleService localeService;

	public List<LocaleDto> mapLocalesToList() {
		List<Locale> locales = localeService.findAll();
		List<LocaleDto> localesDTO = LocaleUtil.mapToLocaleDTOList(locales);
		return localesDTO;
	}
}
