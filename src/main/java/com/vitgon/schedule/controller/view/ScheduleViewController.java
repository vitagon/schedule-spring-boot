package com.vitgon.schedule.controller.view;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.vitgon.schedule.model.Group;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Schedule;
import com.vitgon.schedule.service.GroupService;
import com.vitgon.schedule.service.LocaleService;
import com.vitgon.schedule.service.ScheduleService;
import com.vitgon.schedule.util.ScheduleUtil;
import com.vitgon.schedule.util.model.SubjectsPair;

@Controller
public class ScheduleViewController {
	
	@Autowired
	private LocaleService localeService;
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private GroupService groupService;

	@GetMapping("/{groupId}/schedule")
	public String showScheduleInSingleTable(Model model, HttpServletRequest request, @PathVariable("groupId") int groupId) {
		java.util.Locale loc = (java.util.Locale) request.getSession().getAttribute("URL_LOCALE_ATTRIBUTE_NAME");
		Locale locale = localeService.findByCode(loc.getLanguage());
		
		Group group = groupService.findById(groupId);
		List<Schedule> schedulesList = scheduleService.findByGroup(group);
		Map<String, Map<Integer, SubjectsPair>> subjects = ScheduleUtil.getScheduleMap(schedulesList, locale);
		
		List<String> days = Arrays.asList("monday","tuesday","wednesday","thursday","friday","saturday");
		List<String> bells = Arrays.asList(
				"8.30-10.00",
				"10.10-11.40",
				"11.50-13.20",
				"13.30-15.00",
				"15.10-16.40",
				"16.50-18.20"
		);
		
		
		model.addAttribute("days", days);
		model.addAttribute("bells", bells);
		model.addAttribute("subjects",subjects);
		return "schedule/schedule";
	}
}
