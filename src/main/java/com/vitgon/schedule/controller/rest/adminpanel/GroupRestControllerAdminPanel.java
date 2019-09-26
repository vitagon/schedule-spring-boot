package com.vitgon.schedule.controller.rest.adminpanel;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

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
import com.vitgon.schedule.dto.AddGroupDto;
import com.vitgon.schedule.dto.GroupDto;
import com.vitgon.schedule.model.ApiError;
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.service.GroupDtoService;
import com.vitgon.schedule.service.MessageService;
import com.vitgon.schedule.service.database.GroupService;
import com.vitgon.schedule.service.database.MajorService;


@RestController
@RequestMapping("/api/groups")
public class GroupRestControllerAdminPanel {
	
	private GroupService groupService;
	private MajorService majorService;
	private MessageService messageService;
	private GroupDtoService groupDtoService;

	public GroupRestControllerAdminPanel(
			GroupService groupService, MajorService majorService,
			MessageService messageService, GroupDtoService groupDtoService) {
		this.groupService = groupService;
		this.majorService = majorService;
		this.messageService = messageService;
		this.groupDtoService = groupDtoService;
	}

	@GetMapping("/major-id/{majorId}")
	@ResponseStatus(HttpStatus.OK)
	public List<GroupDto> getGroups(@PathVariable @MajorExists Integer majorId) {
		return groupDtoService.getGroupDtoListByMajorId(majorId);
	}

	@GetMapping("/major-id/{majorId}/locale-id/{localeId}")
	@ResponseStatus(HttpStatus.OK)
	public List<GroupDto> getGroups(@PathVariable @MajorExists Integer majorId,
									@PathVariable @LocaleExists Integer localeId) {
		return groupDtoService.getGroupDtoListByMajorIdAndLocaleId(majorId, localeId);
	}
	
	@GetMapping("/{id}/locale-id/{localeId}")
	@ResponseStatus(HttpStatus.OK)
	public GroupDto getGroup(@PathVariable("id") Integer groupId,
							 @PathVariable Integer localeId) {
		return groupDtoService.getGroupDtoByGroupIdAndLocaleId(groupId, localeId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GroupDto addGroup(@RequestBody @Valid AddGroupDto addGroupDto) {
		Optional<Major> major = majorService.findById(addGroupDto.getMajorId());
		
		if (major == null) {
			throw new IllegalArgumentException("Major was not found!");
		}
		
		Group group = new Group();
		group.setMajor(major.get());
		group.setNumber(addGroupDto.getNumber());
		group.setSuffix(addGroupDto.getSuffix());
		group.setCourseNum(addGroupDto.getCourseNum());
		
		group = groupService.save(group);
		GroupDto groupDto = convertToDto(group);
		return groupDto;
	}
	
	private GroupDto convertToDto(Group group) {
		GroupDto groupDto = new GroupDto(group.getId(), getGroupTitle(group));
		return groupDto;
	}
	
	private String getGroupTitle(Group group) {
		return getDegree(group).charAt(0) + String.valueOf(group.getNumber()) + group.getSuffix();
	}
	
	private String getDegree(Group group) {
		return group.getMajor().getDegree().name();
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void editGroup(@PathVariable("id") @GroupExists Integer groupId,
							  @RequestBody @Valid GroupDto groupDto) {
		Group group = groupService.findById(groupId).get();
		Major major = majorService.findById(groupDto.getMajorId()).get();
		
		group.setNumber(groupDto.getNumber());
		group.setSuffix(groupDto.getSuffix());
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
