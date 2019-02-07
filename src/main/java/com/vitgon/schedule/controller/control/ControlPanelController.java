package com.vitgon.schedule.controller.control;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.vitgon.schedule.model.School;
import com.vitgon.schedule.model.Subject;
import com.vitgon.schedule.model.auth.User;
import com.vitgon.schedule.service.LocaleConverterService;
import com.vitgon.schedule.service.SubjectMapperService;
import com.vitgon.schedule.service.UserMapperService;
import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.service.database.SchoolService;
import com.vitgon.schedule.service.database.SubjectService;
import com.vitgon.schedule.service.database.UserService;
import com.vitgon.schedule.util.LocaleUtil;
import com.vitgon.schedule.util.SchoolUtil;

@Controller
public class ControlPanelController {
	
	private UserService userService;
	private SubjectService subjectService;
	private LocaleService localeService;
	private SchoolService schoolService;
	private LocaleConverterService localeConverterService;
	private SubjectMapperService subjectMapperService;
	private UserMapperService userMapperService;
	
	@Autowired
	public ControlPanelController(UserService userService, SubjectService subjectService, LocaleService localeService,
			SchoolService schoolService, LocaleConverterService localeConverterService,
			SubjectMapperService subjectMapperService, UserMapperService userMapperService) {
		this.userService = userService;
		this.subjectService = subjectService;
		this.localeService = localeService;
		this.schoolService = schoolService;
		this.localeConverterService = localeConverterService;
		this.subjectMapperService = subjectMapperService;
		this.userMapperService = userMapperService;
	}



	@GetMapping("/control")
	public ModelAndView showControlPanel(HttpServletRequest request, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		Locale locale = localeConverterService.getClientLocale(request);
		
		List<School> schools = schoolService.findAll();
		Map<Integer, String> schoolsMap = new HashMap<>();
		for (School school : schools) {
			schoolsMap.put(school.getId(), SchoolUtil.getSchoolTitle(school, locale));
		}
		modelMap.addAttribute("schools", schoolsMap);
		
		List<User> users = userService.findBySpecificRoles(Arrays.asList("teacher".toUpperCase()));
		List<TeacherDTO> teachers = userMapperService.mapToTeacherDTOList(users);
		modelMap.addAttribute("teachers", teachers);
		
		// get locales
		List<Locale> locales = localeService.findAll();
		List<LocaleDTO> localesDTO = LocaleUtil.mapToLocaleDTOList(locales);
		modelMap.addAttribute("locales", localesDTO);
		
		// get subjects
		List<Subject> subjects = subjectService.findAll();
		List<SubjectDTO> subjectsDTO = subjectMapperService.mapToSubjectDTOList(subjects);
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
