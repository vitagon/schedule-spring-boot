package com.vitgon.schedule.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vitgon.schedule.dto.AddMajorDto;
import com.vitgon.schedule.dto.MajorDto;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.service.database.MajorService;

@Service
public class MajorConverterService {
	
	private MajorService majorService;
	private MajorTitleService majorTitleService;
	private LocaleService localeService;

	public List<MajorDto> convertAllToDtoList() {
		List<Major> majors = majorService.findAll();
		List<MajorDto> majorDtoList = new ArrayList<>();
		for (Major major : majors) {
			MajorDto majorDto = new MajorDto(major.getId(), major.getName(), null);
			majorDtoList.add(majorDto);
		}
		return majorDtoList;
	}
	
	public List<MajorDto> convertAllToDtoList(Locale locale) {
		List<Major> majors = majorService.findAll();
		List<MajorDto> majorDtoList = new ArrayList<>();
		for (Major major : majors) {
			String translation = majorTitleService.getMajorTitle(locale, major);
			MajorDto majorDto = new MajorDto(major.getId(), major.getName(), translation);
			majorDtoList.add(majorDto);
		}
		return majorDtoList;
	}
	
	public List<MajorDto> convertAllToDtoList(int localeId) {
		Locale locale = localeService.findById(localeId);
		if (locale == null) {
			throw new IllegalArgumentException(String.format("Locale with id=%d was not found!", localeId));
		}
		return convertAllToDtoList(locale);
	}
}