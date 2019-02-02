package com.vitgon.schedule.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.vitgon.schedule.collection.ScheduleTree;
import com.vitgon.schedule.model.Group;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Schedule;
import com.vitgon.schedule.model.Subject;
import com.vitgon.schedule.model.auth.User;
import com.vitgon.schedule.service.GroupService;
import com.vitgon.schedule.service.LocaleService;
import com.vitgon.schedule.service.ScheduleService;
import com.vitgon.schedule.service.SubjectService;
import com.vitgon.schedule.service.UserService;
import com.vitgon.schedule.util.ScheduleUtil;
import com.vitgon.schedule.util.SubjectUtil;
import com.vitgon.schedule.util.UserUtil;

@Controller
public class ScheduleViewController {
	
	@Autowired
	private LocaleService localeService;
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private GroupService groupService;

	@GetMapping("/{groupId}/schedule")
	public ModelAndView showScheduleInSingleTable(HttpServletRequest request, @PathVariable("groupId") int groupId) {
		java.util.Locale loc = (java.util.Locale) request.getSession().getAttribute("URL_LOCALE_ATTRIBUTE_NAME");
		Locale locale = localeService.findByCode(loc.getLanguage());
		
		Group group = groupService.findById(groupId);
		List<Schedule> schedulesList = scheduleService.findByGroup(group);
		ScheduleTree schedules = ScheduleUtil.getScheduleTree(schedulesList, locale);
		
		List<String> days = Arrays.asList("monday","tuesday","wednesday","thursday","friday","saturday");
		Map<Integer, String> bells = new HashMap<>();
		bells.put(1, "8.30-10.00");
		bells.put(2, "10.10-11.40");
		bells.put(3, "11.50-13.20");
		bells.put(4, "13.30-15.00");
		bells.put(5, "15.10-16.40");
		bells.put(6, "16.50-18.20");
		bells.put(7, "18.30-20.00");
		
		// for modal
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
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("schedule/schedule");
		modelAndView.addObject("teachersNames", teachersNames);
		modelAndView.addObject("days", days);
		modelAndView.addObject("bells", bells);
		modelAndView.addObject("schedules", schedules);
		modelAndView.addObject("subjects", subjectTitles);
		modelAndView.addObject("groupId", groupId);
		return modelAndView;
	}
}
