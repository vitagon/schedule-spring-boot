package com.vitgon.schedule.controller.rest.control;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
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
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.service.database.GroupService;
import com.vitgon.schedule.service.database.MajorService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/control/group")
public class GroupRestControllerControl {
	
	private GroupService groupService;
	private MajorService majorService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ApiSuccess addGroup(@RequestBody @Valid AddGroupDto addGroupDto) {
		Major major = majorService.findById(addGroupDto.getMajorId());
		
		if (major == null) {
			throw new IllegalArgumentException("Major was not found!");
		}
		
		Group group = new Group();
		group.setMajor(major);
		group.setNumber(addGroupDto.getNumber());
		group.setSuffix(addGroupDto.getSuffix());
		group.setCourseNum(addGroupDto.getCourseNum());
		
		groupService.save(group);
		return new ApiSuccess(new Date(), "You successfully added new group!");
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public ApiSuccess editGroup(@RequestBody @Valid EditGroupDto editGroupDto) {
		Group group = groupService.findById(editGroupDto.getId());
		
		if (group == null) {
			throw new IllegalArgumentException("Group was not found!");
		}
		
		group.setCourseNum(editGroupDto.getCourseNum());
		groupService.update(group);
		return new ApiSuccess(new Date(), "You successfully edited group!");
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public ApiSuccess deleteGroup(@RequestParam("id") int id) {
		Group group = groupService.findById(id);
		if (group == null) {
			throw new IllegalArgumentException("Group was not found!");
		}
		groupService.deleteById(id);
		return new ApiSuccess(new Date(), "You successfully removed group!");
	}
}
