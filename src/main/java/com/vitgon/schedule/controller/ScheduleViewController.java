package com.vitgon.schedule.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.service.LocaleConverterService;
import com.vitgon.schedule.service.ScheduleViewService;

@Controller
public class ScheduleViewController {
	
	private LocaleConverterService localeConverterService;
	private ScheduleViewService scheduleViewService;

	@Autowired
	public ScheduleViewController(LocaleConverterService localeConverterService,
								  ScheduleViewService scheduleViewService) {
		this.localeConverterService = localeConverterService;
		this.scheduleViewService = scheduleViewService;
	}

	@GetMapping("/{groupId}/schedule")
	public ModelAndView showScheduleInSingleTable(HttpServletRequest request, @PathVariable("groupId") int groupId) {
		Locale locale = localeConverterService.getClientLocale(request);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("schedule/schedule");
		scheduleViewService.setScheduleViewVars(locale, modelAndView, groupId);
		return modelAndView;
	}
}
