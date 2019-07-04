package com.vitgon.schedule.controller.rest;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vitgon.schedule.dao.GroupDao;
import com.vitgon.schedule.dto.GroupDto;
import com.vitgon.schedule.projection.GroupProjection;
import com.vitgon.schedule.service.GroupDtoService;
import com.vitgon.schedule.service.database.GroupService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/groups")
public class GroupRestController {
	
	private GroupService groupService;
	private GroupDtoService groupDtoService;

	@GetMapping("/major-id/{majorId}/course-num/{courseNum}")
	public List<GroupProjection> getGroups(@PathVariable("majorId") Integer majorId,
										   @PathVariable("courseNum") @Min(1) @Max(6) Integer courseNum) {
		
		List<GroupProjection> groups = groupService.getAllByMajorIdAndCourseNum(majorId, courseNum);
		return groups;
	}
	
	@GetMapping("/major-url/{majorUrl}/locale-id/{localeId}")
	public List<GroupProjection> getGroupsByMajorIdAndLocaleId(@PathVariable("majorUrl") String majorUrl,
															   @PathVariable("localeId") Integer localeId) {
		return groupService.getAllByMajorUrlAndLocaleId(majorUrl, localeId);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<GroupDto> getAll() {
		return groupDtoService.getGroupDtoList();
	}
}
