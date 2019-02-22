package com.vitgon.schedule.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.vitgon.schedule.model.database.Locale;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ControlPanelAttributesService {
	
	private SubjectMapperService subjectMapperService;
	private UserDTOService userDTOService;
	private SchoolMapperService schoolMapperService;
	private LocaleMapperService localeMapperService;

	public void setDataAttributes(ModelMap modelMap, Locale locale) {
		modelMap.addAttribute("schools", schoolMapperService.mapAllSchoolsToMap(locale));
		modelMap.addAttribute("teachers", userDTOService.getTeachersDTO());
		modelMap.addAttribute("locales", localeMapperService.mapLocalesToList());
		modelMap.addAttribute("subjects", subjectMapperService.mapToSubjectDTOList());
		modelMap.addAttribute("schoolDtoList", schoolMapperService.mapAllSchoolsToSchoolDTOList());
	}
}
