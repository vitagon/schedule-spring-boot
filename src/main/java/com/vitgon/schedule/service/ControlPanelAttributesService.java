package com.vitgon.schedule.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.vitgon.schedule.dto.DegreeEnum;
import com.vitgon.schedule.model.database.Locale;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ControlPanelAttributesService {
	
	private SubjectMapperService subjectMapperService;
	private UserDtoService userDTOService;
	private SchoolMapperService schoolMapperService;
	private LocaleMapperService localeMapperService;
	private MajorConverterService majorConverterService;

	public void setDataAttributes(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("schools", schoolMapperService.mapAllSchoolsToMap(locale));
		modelMap.addAttribute("teachers", userDTOService.getTeachersDto());
		modelMap.addAttribute("locales", localeMapperService.mapLocalesToList());
		modelMap.addAttribute("subjects", subjectMapperService.mapToSubjectDTOList());
		modelMap.addAttribute("schoolDtoList", schoolMapperService.mapAllSchoolsToSchoolDTOList());
		modelMap.addAttribute("majorDtoList", majorConverterService.convertAllToDtoList());
		
		modelMap.addAttribute("degrees", createDegreeList());
	}
	
	private List<String> createDegreeList() {
		DegreeEnum[] degreeEnumArr = DegreeEnum.values();
		List<String> degreeList = new ArrayList<>();
		for (DegreeEnum degreeEnum : degreeEnumArr) {
			degreeList.add(degreeEnum.toString());
		}
		return degreeList;
	}
}
