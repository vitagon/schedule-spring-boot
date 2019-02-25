package com.vitgon.schedule.util;

import java.util.ArrayList;
import java.util.List;

import com.vitgon.schedule.dto.LocaleDto;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.resolver.UrlLocaleResolver;

public class LocaleUtil {
	public static List<LocaleDto> mapToLocaleDTOList(List<Locale> locales) {
		List<LocaleDto> localesDTO = new ArrayList<>();
		locales.stream()
			.forEach(locale -> {
				if (!locale.getCode().equals(UrlLocaleResolver.EN)) {
					LocaleDto localeDTO = new LocaleDto();
					localeDTO.setId(locale.getId());
					localeDTO.setCode(locale.getCode());
					localesDTO.add(localeDTO);
				}
			});
		return localesDTO;
	}
}
