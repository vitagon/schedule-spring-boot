package com.vitgon.schedule.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vitgon.schedule.model.Group;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.service.LocaleConverterService;
import com.vitgon.schedule.service.database.GroupService;
import com.vitgon.schedule.util.GroupUtil;

@RestController
public class GroupRestController {
	
	private GroupService groupService;
	private LocaleConverterService localeConverterService;
	
	@Autowired
	public GroupRestController(GroupService groupService,
							   LocaleConverterService localeConverterService) {
		this.groupService = groupService;
		this.localeConverterService = localeConverterService;
	}

	@GetMapping("/api/{majorId}/{courseNum}/groups")
	public Map<Integer, String> getGroups(HttpServletRequest request,
										  @PathVariable("majorId") int majorId,
										  @PathVariable("courseNum") int courseNum) {
		Locale locale = localeConverterService.getClientLocale(request);
		Map<Integer, String> groupsMap = new HashMap<>();
		
		List<Group> groups = groupService.findAllByMajorAndCourseNum(majorId, courseNum);
		if (groups == null) {
			return null;
		}
		for (Group group : groups) {
			groupsMap.put(group.getId(), GroupUtil.getGroupTitle(group, locale));
		}
		return groupsMap;
	}
}
