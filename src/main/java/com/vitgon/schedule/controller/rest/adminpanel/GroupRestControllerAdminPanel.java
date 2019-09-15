package com.vitgon.schedule.controller.rest.adminpanel;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vitgon.schedule.dto.AddGroupDto;
import com.vitgon.schedule.dto.EditGroupDto;
import com.vitgon.schedule.dto.GroupDto;
import com.vitgon.schedule.model.ApiError;
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.service.MessageService;
import com.vitgon.schedule.service.database.GroupService;
import com.vitgon.schedule.service.database.MajorService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/control/group")
public class GroupRestControllerAdminPanel {
	
	private GroupService groupService;
	private MajorService majorService;
	private MessageService messageService;

	public GroupRestControllerAdminPanel(GroupService groupService, MajorService majorService, MessageService messageService) {
		this.groupService = groupService;
		this.majorService = majorService;
		this.messageService = messageService;
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

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public GroupDto editGroup(@RequestBody @Valid EditGroupDto editGroupDto) {
		Optional<Group> groupOpt = groupService.findById(editGroupDto.getId());
		
		if (!groupOpt.isPresent()) {
			throw new IllegalArgumentException("Group was not found!");
		}
		Group group = groupOpt.get();
		group.setCourseNum(editGroupDto.getCourseNum());
		group = groupService.update(group);
		return convertToDto(group);
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
