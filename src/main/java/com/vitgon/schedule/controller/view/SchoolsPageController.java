package com.vitgon.schedule.controller.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vitgon.schedule.controller.BaseController;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.service.LocaleService;
import com.vitgon.schedule.service.SchoolService;

@Controller
public class SchoolsPageController extends BaseController {
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private LocaleService localeService;
	
	@RequestMapping("/schools")
	public String showSchoolsPage(HttpServletRequest request, Model model) {
		java.util.Locale loc = (java.util.Locale) request.getSession().getAttribute("URL_LOCALE_ATTRIBUTE_NAME");
		Locale locale = localeService.findByCode(loc.getLanguage());
		Map<Integer, Map<String,Object>> schools = schoolService.findAllByLocale(locale);
		model.addAttribute("schools", schools);
		return "schools";
	}
}
