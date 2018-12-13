package com.vitgon.schedule.controller.view;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.School;
import com.vitgon.schedule.service.LocaleService;
import com.vitgon.schedule.service.SchoolService;

@Controller
public class MainPageController {
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private LocaleService localeService;
	
	@RequestMapping("/")
	public String showMainPage(Model model) {
		Locale locale = localeService.findByCode("ru_RU");
		Map<Integer, Map<String,Object>> schools = schoolService.findAllByLocale(locale);
		model.addAttribute("schools", schools);
		return "mainPage";
	}
}
