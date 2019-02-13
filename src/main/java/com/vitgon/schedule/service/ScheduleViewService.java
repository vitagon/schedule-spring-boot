package com.vitgon.schedule.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.vitgon.schedule.collection.ScheduleTree;
import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Schedule;
import com.vitgon.schedule.service.database.GroupService;
import com.vitgon.schedule.service.database.ScheduleService;

@Service
public class ScheduleViewService {
	
	private static final Map<Integer, String> BELLS = getBellsMap();
	private static final List<String> DAYS = getDaysList();
	
	private ScheduleService scheduleService;
	private GroupService groupService;
	private ScheduleTreeService scheduleTreeService;
	
	@Autowired
	public ScheduleViewService(ScheduleService scheduleService,
							   GroupService groupService,
							   ScheduleTreeService scheduleTreeService) {
		this.scheduleService = scheduleService;
		this.groupService = groupService;
		this.scheduleTreeService = scheduleTreeService;
	}

	public void setScheduleViewVars(Locale locale, ModelAndView modelAndView, int groupId) {
		Group group = groupService.findById(groupId);
		List<Schedule> schedulesList = scheduleService.findByGroup(group);
		ScheduleTree schedules = scheduleTreeService.getScheduleTree(schedulesList, locale); 
		
		modelAndView.addObject("days", DAYS);
		modelAndView.addObject("bells", BELLS);
		modelAndView.addObject("schedules", schedules);
		modelAndView.addObject("groupId", groupId);
	}
	
	public static Map<Integer, String> getBellsMap() {
		Map<Integer, String> bellsMap = new HashMap<>();
		bellsMap.put(1, "8.30-10.00");
		bellsMap.put(2, "10.10-11.40");
		bellsMap.put(3, "11.50-13.20");
		bellsMap.put(4, "13.30-15.00");
		bellsMap.put(5, "15.10-16.40");
		bellsMap.put(6, "16.50-18.20");
		bellsMap.put(7, "18.30-20.00");
		return bellsMap;
	}
	
	public static List<String> getDaysList() {
		return Arrays.asList("monday","tuesday","wednesday","thursday","friday","saturday");
	}
}
