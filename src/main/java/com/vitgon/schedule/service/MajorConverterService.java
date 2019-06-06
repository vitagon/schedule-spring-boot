package com.vitgon.schedule.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vitgon.schedule.dto.MajorDtoControl;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.service.database.MajorService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MajorConverterService {
	
	private MajorService majorService;
	private MajorTitleService majorTitleService;
	private LocaleService localeService;

	public List<MajorDtoControl> convertToMajorDtoControlList() {
		List<Major> majors = majorService.findAll();
		List<MajorDtoControl> majorDtoControlList = new ArrayList<>();
		for (Major major : majors) {
			MajorDtoControl majorDto = new MajorDtoControl(major.getId(), major.getName(), null);
			majorDtoControlList.add(majorDto);
		}
		return majorDtoControlList;
	}
	
	public List<MajorDtoControl> convertToMajorDtoControlList(Locale locale) {
		List<Major> majors = majorService.findAll();
		List<MajorDtoControl> majorDtoControlList = new ArrayList<>();
		for (Major major : majors) {
			String translation = majorTitleService.getMajorTitle(locale, major);
			MajorDtoControl majorDtoControl = new MajorDtoControl(major.getId(), major.getName(), translation);
			majorDtoControlList.add(majorDtoControl);
		}
		return majorDtoControlList;
	}
	
	public List<MajorDtoControl> convertToMajorDtoControlList(int localeId) {
		Optional<Locale> locale = localeService.findById(localeId);
		if (!locale.isPresent()) {
			throw new IllegalArgumentException(String.format("Locale with id=%d was not found!", localeId));
		}
		return convertToMajorDtoControlList(locale.get());
	}
}