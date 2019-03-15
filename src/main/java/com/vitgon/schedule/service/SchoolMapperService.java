package com.vitgon.schedule.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.vitgon.schedule.dto.MajorDto;
import com.vitgon.schedule.dto.SchoolDto;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.service.database.SchoolService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SchoolMapperService {
	
	private SchoolService schoolService;
	private SchoolTitleService schoolTitleService;
	private MajorTitleService majorTitleService;
	
	public List<SchoolDto> mapAllToSchoolDTOList() {
		List<School> schools = schoolService.findAll();
		List<SchoolDto> schoolsDTOList = new ArrayList<>();
		for (School school : schools) {
			SchoolDto schoolDTO = new SchoolDto(school.getId(), school.getName(), null);
			schoolsDTOList.add(schoolDTO);
		}
		return schoolsDTOList;
	} 
	
	public List<SchoolDto> mapAllToSchoolDTOList(Locale locale) {
		List<School> schools = schoolService.findAll();
		List<SchoolDto> schoolsDTOList = new ArrayList<>();
		for (School school : schools) {
			String title = schoolTitleService.getSchoolTitle(locale, school);
			SchoolDto schoolDTO = new SchoolDto(school.getId(), school.getName(), title);
			schoolsDTOList.add(schoolDTO);
		}
		return schoolsDTOList;
	} 

	public Map<Integer, String> mapAllToMap(Locale locale) {
		List<School> schools = schoolService.findAll();
		Map<Integer, String> schoolsMap = new HashMap<>();
		for (School school : schools) {
			schoolsMap.put(school.getId(), schoolTitleService.getSchoolTitle(locale, school));
		}
		return schoolsMap;
	}
	
	public List<SchoolDto> prepareSchoolPojos(List<School> schools, Locale locale) {
		List<SchoolDto> schoolDtoList = new ArrayList<>();
		
		for (School school : schools) {
			SchoolDto schoolDto = new SchoolDto();
			schoolDto.setId(school.getId());
			schoolDto.setUrl(school.getUrl());
			schoolDto.setTranslation(schoolTitleService.getSchoolTitle(locale, school));
			
			List<Major> majors = school.getMajors();
			if (majors != null) {
				List<MajorDto> majorDtoList = new ArrayList<>();
				for (Major major : majors) {
					MajorDto majorDto = new MajorDto();
					majorDto.setTranslation(majorTitleService.getMajorTitle(locale, major));
					majorDto.setUrl(major.getUrl());
					
					majorDtoList.add(majorDto);
				}
				schoolDto.setMajors(majorDtoList);
			}
			
			schoolDtoList.add(schoolDto);
		}
		return schoolDtoList;
	}
}
