package com.vitgon.schedule.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vitgon.schedule.dto.SchoolDto;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.resolver.UrlLocaleResolver;
import com.vitgon.schedule.service.LocaleConverterService;
import com.vitgon.schedule.service.SchoolMapperService;
import com.vitgon.schedule.service.database.SchoolService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class SchoolsViewController {
	
	private SchoolMapperService schoolMapperService;
	private SchoolService schoolService;
	private LocaleConverterService localeConverterService;
	
	@RequestMapping("/schools")
	public String showSchoolsPage(HttpServletRequest request, Model model) {
		Locale locale = localeConverterService.getClientLocale(request);
		
		List<School> schools;
		if (locale.getCode().equals(UrlLocaleResolver.EN)) {
			schools = schoolService.findAll();
		} else {
			schools = schoolService.findAllByLocale(locale);
		}
		
		List<SchoolDto> preparedSchools = schoolMapperService.prepareSchoolPojos(schools, locale);
		model.addAttribute("schools", preparedSchools);
		return "schools";
	}
}
