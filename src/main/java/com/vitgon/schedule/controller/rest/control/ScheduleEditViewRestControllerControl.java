package com.vitgon.schedule.controller.rest.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.service.EditScheduleViewService;
import com.vitgon.schedule.service.LocaleConverterService;

@RestController
public class ScheduleEditViewRestControllerControl {
	
	private LocaleConverterService localeConverterService;
	private EditScheduleViewService editScheduleViewService;

	@Autowired
	public ScheduleEditViewRestControllerControl(LocaleConverterService localeConverterService,
										  EditScheduleViewService editScheduleViewService) {
		this.localeConverterService = localeConverterService;
		this.editScheduleViewService = editScheduleViewService;
	}

	@GetMapping("/api/{groupId}/schedule/edit")
	public ModelAndView getScheduleEditViewFragment(HttpServletRequest request, @PathVariable("groupId") int groupId) {
		Locale locale = localeConverterService.getClientLocale(request);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("control/edit-schedule :: target");
		editScheduleViewService.addEditScheduleViewVars(locale, modelAndView, groupId);
		return modelAndView;
	}
}
