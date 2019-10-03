package com.vitgon.schedule.controller.rest;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitgon.schedule.dto.UserDto;
import com.vitgon.schedule.service.UserDtoService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
	private UserDtoService userDtoService;

	public UserRestController(UserDtoService userDtoService) {
		this.userDtoService = userDtoService;
	}

	@GetMapping("/pageable/role/{role}")
	public List<UserDto> getAllByRole(
			@PathVariable String role,
			@PageableDefault(page=0, size = 10, sort = {"id"}, direction = Direction.ASC) Pageable pageable) {
		return userDtoService.getUserDtoListByRole(role, pageable);
	}
	
	@GetMapping("/role/{role}")
	public List<UserDto> getAllByRole(@PathVariable String role) {
		return userDtoService.getUserDtoListByRole(role);
	}
}
