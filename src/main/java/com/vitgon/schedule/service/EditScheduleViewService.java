package com.vitgon.schedule.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.vitgon.schedule.model.database.Locale;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EditScheduleViewService {
	
	private ScheduleViewService scheduleViewService;
	private SubjectMapperService subjectMapperService;
	private UserMapperService userMapperService;

	public void addEditScheduleViewVars(Locale locale, ModelAndView modelAndView, int groupId) {
		scheduleViewService.setScheduleViewVars(locale, modelAndView, groupId);
		modelAndView.addObject("teachersNames", userMapperService.mapAllToMap(locale));
		modelAndView.addObject("subjects", subjectMapperService.mapToMap(locale, true));
	}
}
