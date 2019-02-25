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
	
	private LocaleConverterService localeConverterService;
	private ControlPanelAttributesService controlPanelAttributesService;

	@GetMapping
	public ModelAndView showControlPanel(HttpServletRequest request, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		Locale locale = localeConverterService.getClientLocale(request);
		controlPanelAttributesService.setDataAttributes(modelMap, locale);
		
		if (!modelMap.containsAttribute("addSubjectDTO")) {
			modelAndView.addObject(new AddSubjectDto());
		}
		
		if (!modelMap.containsAttribute("addSubjectTranslationDTO")) {
			modelAndView.addObject(new AddSubjectTranslationDto());
		}
		
		if (!modelMap.containsAttribute("addTeacherTranslationDTO")) {
			modelAndView.addObject(new AddTeacherTranslationDto());
		}
		
		if (!modelMap.containsAttribute("activeTab")) {
			modelAndView.addObject("activeTab", "main");
		}
		
		modelAndView.setViewName("control/control-panel");
		return modelAndView;
	}
}
