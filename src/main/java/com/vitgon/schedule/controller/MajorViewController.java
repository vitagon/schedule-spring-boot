package com.vitgon.schedule.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vitgon.schedule.dto.GroupDto;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.service.GroupDtoService;
import com.vitgon.schedule.service.database.MajorService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class MajorViewController {
	
	private MajorService majorService;
	private GroupDtoService groupMapperService;
	
	@RequestMapping("/school/{school}/major/{major}")
	public String showMajorGroups(@PathVariable("major") String majorUrl, HttpServletRequest request, Model model) {
		Optional<Major> major = majorService.findByUrl(majorUrl);
		Map<Integer, List<GroupDto>> groupsMap = groupMapperService.convertToGroupDtoMap(major.get().getGroups());
		
		model.addAttribute("groupsMap", groupsMap);
		return "major-groups";
	}
}
