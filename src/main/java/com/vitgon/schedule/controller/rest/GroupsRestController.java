package com.vitgon.schedule.controller.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vitgon.schedule.dto.GroupDto;
import com.vitgon.schedule.service.GroupMapperService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/groups")
public class GroupsRestController {
	
	private GroupMapperService groupMapperService;

	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public List<GroupDto> getAll() {
		return groupMapperService.convertToGroupDtoList();
	}
}
