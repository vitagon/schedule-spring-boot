package com.vitgon.schedule.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.vitgon.schedule.dto.MajorDto;
import com.vitgon.schedule.dto.SchoolDto;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.projection.SchoolProjection;
import com.vitgon.schedule.service.database.SchoolService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SchoolDtoService {
	
	private SchoolService schoolService;
	private LocaleConverterService localeConverterService;
	
//	public List<SchoolDto> convertToSchoolDtoList() {
//		List<School> schools = schoolService.findAll();
//		List<SchoolDto> schoolsDTOList = new ArrayList<>();
//		for (School school : schools) {
//			SchoolDto schoolDTO = new SchoolDto(school.getId(), school.getName());
//			schoolsDTOList.add(schoolDTO);
//		}
//		return schoolsDTOList;
//	} 
//	
//	public List<SchoolDto> convertToSchoolDtoList(Locale locale) {
//		List<School> schools = schoolService.findAll();
//		List<SchoolDto> schoolsDtoList = new ArrayList<>();
//		for (School school : schools) {
//			String title = schoolTitleService.getSchoolTitle(locale, school);
//			SchoolDto schoolDto = new SchoolDto(school.getId(), title);
//			schoolsDtoList.add(schoolDto);
//		}
//		return schoolsDtoList;
//	} 
//
//	public Map<Integer, String> convertToMap(Locale locale) {
//		List<School> schools = schoolService.findAll();
//		Map<Integer, String> schoolsMap = new HashMap<>();
//		for (School school : schools) {
//			schoolsMap.put(school.getId(), StringUtil.capitalizeFirstLetter(school.getName()));
//		}
//		return schoolsMap;
//	}
	
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
	
	public List<SchoolDto> getSchoolDtoListForAdminPanel(List<SchoolProjection> schools) {
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
	
//	public List<SchoolDtoControl> convertToSchoolDtoControlList() {
//		List<School> schools = schoolService.findAll();
//		List<SchoolDtoControl> schoolsDTOControlList = new ArrayList<>();
//		for (School school : schools) {
//			SchoolDtoControl schoolDtoControl = new SchoolDtoControl(school.getId(), school.getName(), null);
//			schoolsDTOControlList.add(schoolDtoControl);
//		}
//		return schoolsDTOControlList;
//	}
//	
//	public List<SchoolDtoControl> convertToSchoolDtoControlList(Locale locale) {
//		List<School> schools = schoolService.findAll();
//		List<SchoolDtoControl> schoolsDTOControlList = new ArrayList<>();
//		for (School school : schools) {
//			String title = schoolTitleService.getSchoolTitle(locale, school);
//			SchoolDtoControl schoolDtoControl = new SchoolDtoControl(school.getId(), school.getName(), title);
//			schoolsDTOControlList.add(schoolDtoControl);
//		}
//		return schoolsDTOControlList;
//	} 
}
