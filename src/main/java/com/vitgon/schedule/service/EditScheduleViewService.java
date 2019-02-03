package com.vitgon.schedule.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Subject;
import com.vitgon.schedule.model.auth.User;
import com.vitgon.schedule.service.database.SubjectService;
import com.vitgon.schedule.service.database.UserService;
import com.vitgon.schedule.util.SubjectUtil;
import com.vitgon.schedule.util.UserUtil;

@Service
public class EditScheduleViewService {
	
	private ScheduleViewService scheduleViewService;
	private UserService userService;
	private SubjectService subjectService;
	
	@Autowired
	public EditScheduleViewService(ScheduleViewService scheduleViewService,
								   UserService userService,
								   SubjectService subjectService) {
		this.scheduleViewService = scheduleViewService;
		this.userService = userService;
		this.subjectService = subjectService;
	}

	public void addEditScheduleViewVars(Locale locale, ModelAndView modelAndView, int groupId) {
		// set model vars that we use for rendering schedule
		scheduleViewService.setScheduleViewVars(locale, modelAndView, groupId);
		
		List<User> users = userService.findAll();
		Map<Integer, String> teachersNames = new HashMap<>();
		for (User teacher : users) {
			teachersNames.put(teacher.getId(), UserUtil.makeupUsername(teacher, locale));
		}
		
		List<Subject> subjects = subjectService.findAll();
		Map<Integer, String> subjectTitles = new HashMap<>();
		for (Subject subject : subjects) {
			subjectTitles.put(subject.getId(), SubjectUtil.getSubjectTitle(subject, locale));
		}
		
		modelAndView.addObject("teachersNames", teachersNames);
		modelAndView.addObject("subjects", subjectTitles);
	}
}
