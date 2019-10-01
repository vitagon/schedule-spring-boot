package com.vitgon.schedule.controller.rest;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vitgon.schedule.annotation.validation.GroupExists;
import com.vitgon.schedule.annotation.validation.LocaleExists;
import com.vitgon.schedule.annotation.validation.MajorExists;
import com.vitgon.schedule.dto.GroupDto;
import com.vitgon.schedule.model.ApiError;
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.projection.GroupProjection;
import com.vitgon.schedule.service.GroupDtoService;
import com.vitgon.schedule.service.MessageService;
import com.vitgon.schedule.service.database.GroupService;
import com.vitgon.schedule.service.database.MajorService;

@RestController
@RequestMapping("/api/groups")
public class GroupRestController {
	
	private GroupService groupService;
	private MajorService majorService;
	private MessageService messageService;
	private GroupDtoService groupDtoService;

	public GroupRestController(
			GroupService groupService, MajorService majorService,
			MessageService messageService, GroupDtoService groupDtoService) {
		this.groupService = groupService;
		this.majorService = majorService;
		this.messageService = messageService;
		this.groupDtoService = groupDtoService;
	}

	@GetMapping("/major-id/{majorId}/course-num/{courseNum}")
	public List<GroupProjection> getGroupsByMajorIdAndCourseNum(@PathVariable("majorId") Integer majorId,
										   @PathVariable("courseNum") @Min(1) @Max(6) Integer courseNum) {
		
		List<GroupProjection> groups = groupService.getAllByMajorIdAndCourseNum(majorId, courseNum);
		return groups;
	}
	
	@GetMapping("/major-url/{majorUrl}/locale-id/{localeId}")
	public List<GroupProjection> getGroupsByMajorUrlAndLocaleId(@PathVariable("majorUrl") String majorUrl,
															   @PathVariable("localeId") Integer localeId) {
		return groupService.getAllByMajorUrlAndLocaleId(majorUrl, localeId);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<GroupDto> getAll() {
		return groupDtoService.getGroupDtoList();
	}
	
	@GetMapping("/major-id/{majorId}")
	@ResponseStatus(HttpStatus.OK)
	public List<GroupDto> getGroups(@PathVariable @MajorExists Integer majorId) {
		return groupDtoService.getGroupDtoListByMajorId(majorId);
	}

	@GetMapping("/major-id/{majorId}/locale-id/{localeId}")
	@ResponseStatus(HttpStatus.OK)
	public List<GroupDto> getGroupsByMajorIdAndLocaleId(@PathVariable @MajorExists Integer majorId,
									@PathVariable @LocaleExists Integer localeId) {
		return groupDtoService.getGroupDtoListByMajorIdAndLocaleId(majorId, localeId);
	}
	
	@GetMapping("/{id}/locale-id/{localeId}")
	@ResponseStatus(HttpStatus.OK)
	public GroupDto getGroupByGroupIdAndLocaleId(@PathVariable("id") Integer groupId,
							 @PathVariable Integer localeId) {
		return groupDtoService.getGroupDtoByGroupIdAndLocaleId(groupId, localeId);
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public GroupDto getGroupById(@PathVariable("id") Integer groupId) {
		return groupDtoService.getGroupDtoByGroupId(groupId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GroupDto addGroup(@RequestBody @Valid GroupDto groupDto) {
		Optional<Major> major = majorService.findById(groupDto.getMajorId());
		
		Group group = new Group();
		group.setMajor(major.get());
		group.setName(groupDto.getName());
		group.setCourseNum(groupDto.getCourseNum());
		
		group = groupService.save(group);
		groupDto = convertToDto(group);
		return groupDto;
	}
	
	private GroupDto convertToDto(Group group) {
		GroupDto groupDto = new GroupDto();
		groupDto.setId(group.getId());
		groupDto.setName(group.getName());
		groupDto.setCourseNum(group.getCourseNum());
		groupDto.setMajorId(group.getMajor().getId());
		return groupDto;
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void editGroup(@PathVariable("id") @GroupExists Integer groupId,
							  @RequestBody @Valid GroupDto groupDto) {
		Group group = groupService.findById(groupId).get();
		Major major = majorService.findById(groupDto.getMajorId()).get();
		
		group.setName(groupDto.getName());
		group.setCourseNum(groupDto.getCourseNum());
		group.setMajor(major);
		groupService.update(group);
	}
	
	@DeleteMapping(params = {"id"})
	public ResponseEntity<?> deleteGroup(@RequestParam("id") int id) {
		Optional<Group> group = groupService.findById(id);
		if (!group.isPresent()) {
			Map<String, List<String>> errors = new HashMap<>();
			errors.put("id", Arrays.asList(messageService.getMessage("chooseValue")));
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ApiError(new Date(), "Subject Not Found", errors));
		}
		groupService.deleteById(id);
		return ResponseEntity.ok(new ApiSuccess(new Date(), "You successfully removed group!"));
	}
}
