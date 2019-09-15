package com.vitgon.schedule.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vitgon.schedule.dto.GroupDto;
import com.vitgon.schedule.service.GroupDtoService;

import lombok.AllArgsConstructor;

@Controller
public class GroupsViewController {
	
	private GroupDtoService groupDtoService;

	public GroupsViewController(GroupDtoService groupDtoService) {
		this.groupDtoService = groupDtoService;
	}

	@RequestMapping("/school/{school}/major/{major}")
	public String showMajorGroups(@PathVariable("major") String majorUrl, Model model) {
		Map<Integer, List<GroupDto>> groupsMap = groupDtoService.getGroupDtoMap(majorUrl);
		
		model.addAttribute("groupsMap", groupsMap);
		return "major-groups";
	}
}
