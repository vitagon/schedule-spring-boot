package com.vitgon.schedule.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.vitgon.schedule.dto.SchoolDto;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.pojo.MajorPOJO;
import com.vitgon.schedule.pojo.SchoolPOJO;
import com.vitgon.schedule.resolver.UrlLocaleResolver;
import com.vitgon.schedule.service.database.SchoolService;
import com.vitgon.schedule.util.MajorUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SchoolMapperService {
	
	private SchoolService schoolService;
	private SchoolTitleService schoolTitleService;
	
	public List<SchoolDto> mapAllSchoolsToSchoolDTOList() {
		List<School> schools = schoolService.findAll();
		List<SchoolDto> schoolsDTOList = new ArrayList<>();
		for (School school : schools) {
			SchoolDto schoolDTO = new SchoolDto(school.getId(), school.getName(), null);
			schoolsDTOList.add(schoolDTO);
		}
		return schoolsDTOList;
	} 
	
	public List<SchoolDto> mapAllSchoolsToSchoolDTOList(Locale locale) {
		List<School> schools = schoolService.findAll();
		List<SchoolDto> schoolsDTOList = new ArrayList<>();
		for (School school : schools) {
			String title = schoolTitleService.getSchoolTitle(locale, school);
			SchoolDto schoolDTO = new SchoolDto(school.getId(), school.getName(), title);
			schoolsDTOList.add(schoolDTO);
		}
		return schoolsDTOList;
	} 

	public Map<Integer, String> mapAllSchoolsToMap(Locale locale) {
		List<School> schools = schoolService.findAll();
		Map<Integer, String> schoolsMap = new HashMap<>();
		for (School school : schools) {
			schoolsMap.put(school.getId(), schoolTitleService.getSchoolTitle(locale, school));
		}
		return schoolsMap;
	}
	
	public List<SchoolPOJO> prepareSchoolPojos(List<School> schools, Locale locale) {
		
		List<SchoolPOJO> schoolsPojos = new ArrayList<>();
		
		for (School school : schools) {
			SchoolPOJO schoolPojo = new SchoolPOJO();
			schoolPojo.setId(school.getId());
			schoolPojo.setUrl(school.getUrl());
			schoolPojo.setTitle(schoolTitleService.getSchoolTitle(locale, school));
			
			List<Major> majors = school.getMajors();
			if (majors != null) {
				List<MajorPOJO> majorsPojos = new ArrayList<>();
				for (Major major : majors) {
					MajorPOJO majorPojo = new MajorPOJO();
					majorPojo.setTitle(MajorUtil.getMajorTitle(major, locale));
					majorPojo.setUrl(major.getUrl());
					
					majorsPojos.add(majorPojo);
				}
				schoolPojo.setMajors(majorsPojos);
			}
			
			schoolsPojos.add(schoolPojo);
		}
		return schoolsPojos;
	}
}
