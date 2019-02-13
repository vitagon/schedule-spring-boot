package com.vitgon.schedule.util;

import java.util.ArrayList;
import java.util.List;

import com.vitgon.schedule.dto.LocaleDTO;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.resolver.UrlLocaleResolver;

public class LocaleUtil {
	public static List<LocaleDTO> mapToLocaleDTOList(List<Locale> locales) {
		List<LocaleDTO> localesDTO = new ArrayList<>();
		locales.stream()
			.forEach(locale -> {
				if (!locale.getCode().equals(UrlLocaleResolver.EN)) {
					LocaleDTO localeDTO = new LocaleDTO();
					localeDTO.setId(locale.getId());
					localeDTO.setCode(locale.getCode());
					localesDTO.add(localeDTO);
				}
			});
		return localesDTO;
	}
}
