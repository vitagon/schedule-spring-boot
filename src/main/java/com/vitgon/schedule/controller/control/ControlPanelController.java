package com.vitgon.schedule.controller.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vitgon.schedule.dto.AddSubjectDto;
import com.vitgon.schedule.dto.AddSubjectTranslationDto;
import com.vitgon.schedule.dto.AddTeacherTranslationDto;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.service.ControlPanelAttributesService;
import com.vitgon.schedule.service.LocaleConverterService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/control")
public class ControlPanelController {
	
//	private LocaleConverterService localeConverterService;
//	private ControlPanelAttributesService controlPanelAttributesService;

	@GetMapping
	public String showControlPanel(HttpServletRequest request) {
//		ModelAndView modelAndView = new ModelAndView();
//		Locale locale = localeConverterService.getClientLocale(request);
//		controlPanelAttributesService.setDataAttributes(modelMap, locale);
//		
//		modelAndView.addObject(new AddSubjectDto());
//		modelAndView.addObject(new AddSubjectTranslationDto());
//		modelAndView.addObject(new AddTeacherTranslationDto());
//		modelAndView.addObject("activeTab", "main");
//		
//		modelAndView.setViewName("control/control-panel");
//		return modelAndView;
		return "forward:/admin-panel.html";
	}
}
