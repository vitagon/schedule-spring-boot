package com.vitgon.schedule.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vitgon.schedule.dto.MajorDto;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.service.database.MajorService;

@Service
public class MajorMapperService {
	
	private MajorService majorService;
	private MajorTitleService majorTitleService;

	public List<MajorDto> mapAllMajorsToMajorDtoList() {
		List<Major> majors = majorService.findAll();
		List<MajorDto> majorDtoList = new ArrayList<>();
		for (Major major : majors) {
			MajorDto majorDto = new MajorDto(major.getId(), major.getName(), null);
			majorDtoList.add(majorDto);
		}
		return majorDtoList;
	}
	
	public List<MajorDto> mapAllMajorsToMajorDtoList(Locale locale) {
		List<Major> majors = majorService.findAll();
		List<MajorDto> majorDtoList = new ArrayList<>();
		for (Major major : majors) {
			String translation = majorTitleService.getMajorTitle(locale, major);
			MajorDto majorDto = new MajorDto(major.getId(), major.getName(), translation);
			majorDtoList.add(majorDto);
		}
		return majorDtoList;
	}
}