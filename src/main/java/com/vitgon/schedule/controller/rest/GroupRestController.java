package com.vitgon.schedule.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.service.GroupMapperService;
import com.vitgon.schedule.service.database.GroupService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class GroupRestController {
	
	private GroupService groupService;
	private GroupMapperService groupMapperService;

	@GetMapping("/api/{majorId}/{courseNum}/groups")
	public Map<Integer, String> getGroups(@PathVariable("majorId") int majorId,
										  @PathVariable("courseNum") int courseNum) {
		Map<Integer, String> groupsMap = new HashMap<>();
		
		List<Group> groups = groupService.findAllByMajorAndCourseNum(majorId, courseNum);
		if (groups == null) {
			return null;
		}
		for (Group group : groups) {
			groupsMap.put(group.getId(), groupMapperService.getGroupTitle(group));
		}
		return groupsMap;
	}
}
