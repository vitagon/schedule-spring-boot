package com.vitgon.schedule.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.School;
import com.vitgon.schedule.pojo.SchoolPOJO;
import com.vitgon.schedule.resolver.UrlLocaleResolver;
import com.vitgon.schedule.service.LocaleService;
import com.vitgon.schedule.service.SchoolService;
import com.vitgon.schedule.util.SchoolUtil;

@Controller
public class SchoolsViewController {
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private LocaleService localeService;
	
	@RequestMapping("/schools")
	public String showSchoolsPage(HttpServletRequest request, Model model) {
		java.util.Locale loc = (java.util.Locale) request.getSession().getAttribute("URL_LOCALE_ATTRIBUTE_NAME");
		Locale locale = localeService.findByCode(loc.getLanguage());
		
		List<School> schools;
		if (locale.getCode().equals(UrlLocaleResolver.EN)) {
			schools = schoolService.findAll();
		} else {
			schools = schoolService.findAllByLocale(locale);
		}
		
		List<SchoolPOJO> preparedSchools = SchoolUtil.prepareSchoolPojos(schools, locale);
		model.addAttribute("schools", preparedSchools);
		return "schools";
	}
}
