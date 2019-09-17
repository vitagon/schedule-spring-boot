package com.vitgon.schedule.service;

import com.vitgon.schedule.dto.MajorDto;
import com.vitgon.schedule.dto.SchoolDto;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.projection.SchoolProjection;
import com.vitgon.schedule.service.database.SchoolService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SchoolDtoService {
	
	private SchoolService schoolService;
	private LocaleConverterService localeConverterService;

	public SchoolDtoService(SchoolService schoolService, LocaleConverterService localeConverterService) {
		this.schoolService = schoolService;
		this.localeConverterService = localeConverterService;
	}

	public List<SchoolDto> getSchoolDtoListByLocaleIdForAdminPanel() {
		Locale locale = localeConverterService.getClientLocale();
		return getSchoolDtoListByLocaleIdForAdminPanel(locale.getId());
	}
	
	public List<SchoolDto> getSchoolDtoListByLocaleIdForAdminPanel(Integer localeId) {
		
		List<SchoolProjection> schools = schoolService.getAllJoiningWithMajors(localeId);
		List<SchoolDto> schoolDtoList;
		Map<Integer, SchoolDto> schoolDtoMap = new HashMap<>();
		
		for (SchoolProjection school : schools) {
			
			SchoolDto schoolDto;
			// check if we inserted this school
			if (!schoolDtoMap.containsKey(school.getId())) {
				schoolDto = new SchoolDto();
				schoolDto.setId(school.getId());
				schoolDto.setUrl(school.getUrl());
				schoolDto.setName(school.getName());
				schoolDto.setTranslation(school.getTranslation());
				schoolDtoMap.put(school.getId(), schoolDto);
			}
		}
		
		schoolDtoList = new ArrayList<>(schoolDtoMap.values());
		return schoolDtoList;
	}
	
	public List<SchoolDto> getSchoolDtoListForPublic(List<SchoolProjection> schools) {
		List<SchoolDto> schoolDtoList;
		Map<Integer, SchoolDto> schoolDtoMap = new HashMap<>();
		
		for (SchoolProjection school : schools) {
			
			SchoolDto schoolDto;
			// check if we inserted this school
			if (!schoolDtoMap.containsKey(school.getId())) {
				schoolDto = new SchoolDto();
				schoolDto.setId(school.getId());
				schoolDto.setUrl(school.getUrl());
				schoolDto.setName(school.getTranslation());
				schoolDtoMap.put(school.getId(), schoolDto);
			} else {
				schoolDto = schoolDtoMap.get(school.getId());
			}
			
			List<MajorDto> majorDtoList = schoolDto.getMajors();
			MajorDto majorDto = new MajorDto();
			majorDto.setId(school.getMajor_id());
			majorDto.setName(school.getMajor_translation());
			majorDto.setUrl(school.getMajor_url());
			majorDtoList.add(majorDto);
		}
		
		schoolDtoList = new ArrayList<>(schoolDtoMap.values());
		return schoolDtoList;
	}
}
