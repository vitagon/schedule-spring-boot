package com.vitgon.schedule.controller.rest;

import com.vitgon.schedule.dto.GroupDto;
import com.vitgon.schedule.projection.GroupProjection;
import com.vitgon.schedule.service.GroupDtoService;
import com.vitgon.schedule.service.database.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupRestController {
	
	private GroupService groupService;
	private GroupDtoService groupDtoService;

	public GroupRestController(GroupService groupService, GroupDtoService groupDtoService) {
		this.groupService = groupService;
		this.groupDtoService = groupDtoService;
	}

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
