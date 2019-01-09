package com.vitgon.schedule.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Teacher;
import com.vitgon.schedule.service.LocaleService;
import com.vitgon.schedule.service.TeacherService;
import com.vitgon.schedule.util.TeacherUtil;

@Controller
@RequestMapping("/api")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private LocaleService localeService;
	
	@Autowired
	private HttpServletRequest request;
	
	@GetMapping("/teachers/all")
	@ResponseBody
	public List<String> getAllTeachers() {
		java.util.Locale loc = (java.util.Locale) request.getSession().getAttribute("URL_LOCALE_ATTRIBUTE_NAME");
		Locale locale = localeService.findByCode(loc.getLanguage());
		
		List<Teacher> teachers = teacherService.findAll();
		List<String> teachersNames = new ArrayList<>();
		for (Teacher teacher : teachers) {
			teachersNames.add(TeacherUtil.makeUpTeacherName(teacher, locale));
		}
		return teachersNames;
	}
}
