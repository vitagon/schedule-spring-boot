package com.vitgon.schedule.controller;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.service.LocaleConverterService;
import com.vitgon.schedule.service.ScheduleViewService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ScheduleViewController {
	
	private LocaleConverterService localeConverterService;
	private ScheduleViewService scheduleViewService;

	public ScheduleViewController(LocaleConverterService localeConverterService, ScheduleViewService scheduleViewService) {
		this.localeConverterService = localeConverterService;
		this.scheduleViewService = scheduleViewService;
	}

	@GetMapping("/schedule/group-id/{groupId}")
	public ModelAndView showScheduleInSingleTable(@PathVariable("groupId") Integer groupId) {
		Locale locale = localeConverterService.getClientLocale();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("schedule/schedule");
		scheduleViewService.setScheduleViewVars(locale, modelAndView, groupId);
		return modelAndView;
	}
}
