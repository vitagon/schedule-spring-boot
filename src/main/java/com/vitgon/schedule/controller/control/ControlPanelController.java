package com.vitgon.schedule.controller.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vitgon.schedule.dto.AddSubjectDTO;
import com.vitgon.schedule.dto.AddTeacherTranslationDTO;
import com.vitgon.schedule.dto.LocaleDTO;
import com.vitgon.schedule.dto.TeacherDTO;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.auth.User;
import com.vitgon.schedule.resolver.UrlLocaleResolver;
import com.vitgon.schedule.service.LocaleService;
import com.vitgon.schedule.service.UserService;
import com.vitgon.schedule.util.UserUtil;

@Controller
public class ControlPanelController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LocaleService localeService;
	
	@GetMapping("/control")
	public ModelAndView showControlPanel(ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		
		List<User> users = userService.findBySpecificRoles(Arrays.asList("teacher".toUpperCase()));
		List<TeacherDTO> teachers = users.stream()
				.map(user -> {
					TeacherDTO teacherDTO = new TeacherDTO();
					teacherDTO.setId(user.getId());
					teacherDTO.setName(UserUtil.makeupUsername(user));
					return teacherDTO;
				})
				.collect(Collectors.toList());
		modelMap.addAttribute("teachers", teachers);
		
		// get locales
		List<Locale> locales = localeService.findAll();
		List<LocaleDTO> localesDTO = new ArrayList<>();
		locales.stream()
				.forEach(locale -> {
					if (!locale.getCode().equals(UrlLocaleResolver.EN)) {
						LocaleDTO localeDTO = new LocaleDTO();
						localeDTO.setId(locale.getId());
						localeDTO.setCode(locale.getCode());
						localesDTO.add(localeDTO);
					}
				});
		modelMap.addAttribute("locales", localesDTO);
		
		if (!modelMap.containsAttribute("addSubjectDTO")) {
			modelAndView.addObject(new AddSubjectDTO());
		}
		
		if (!modelMap.containsAttribute("addTeacherTranslationDTO")) {
			modelAndView.addObject(new AddTeacherTranslationDTO());
		}
		
		if (!modelMap.containsAttribute("activeTab")) {
			modelAndView.addObject("activeTab", "main");
		}
		
		modelAndView.setViewName("control/control-panel");
		return modelAndView;
	}
}
