package com.vitgon.schedule.controller.control;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vitgon.schedule.dto.AddSubjectDTO;
import com.vitgon.schedule.dto.AddSubjectTranslationDTO;
import com.vitgon.schedule.dto.AddTeacherTranslationDTO;
import com.vitgon.schedule.dto.LocaleDTO;
import com.vitgon.schedule.dto.SubjectDTO;
import com.vitgon.schedule.dto.TeacherDTO;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Subject;
import com.vitgon.schedule.model.auth.User;
import com.vitgon.schedule.service.LocaleService;
import com.vitgon.schedule.service.SubjectService;
import com.vitgon.schedule.service.UserService;
import com.vitgon.schedule.util.LocaleUtil;
import com.vitgon.schedule.util.SubjectUtil;
import com.vitgon.schedule.util.UserUtil;

@Controller
public class ControlPanelController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private LocaleService localeService;
	
	@GetMapping("/control")
	public ModelAndView showControlPanel(ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		
		List<User> users = userService.findBySpecificRoles(Arrays.asList("teacher".toUpperCase()));
		List<TeacherDTO> teachers = UserUtil.mapToTeacherDTOList(users);
		modelMap.addAttribute("teachers", teachers);
		
		// get locales
		List<Locale> locales = localeService.findAll();
		List<LocaleDTO> localesDTO = LocaleUtil.mapToLocaleDTOList(locales);
		modelMap.addAttribute("locales", localesDTO);
		
		// get subjects
		List<Subject> subjects = subjectService.findAll();
		List<SubjectDTO> subjectsDTO = SubjectUtil.mapToSubjectDTOList(subjects);
		modelMap.addAttribute("subjects", subjectsDTO);
		
		if (!modelMap.containsAttribute("addSubjectDTO")) {
			modelAndView.addObject(new AddSubjectDTO());
		}
		
		if (!modelMap.containsAttribute("addSubjectTranslationDTO")) {
			modelAndView.addObject(new AddSubjectTranslationDTO());
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
