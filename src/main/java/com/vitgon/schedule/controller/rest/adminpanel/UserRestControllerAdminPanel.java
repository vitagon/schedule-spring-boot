package com.vitgon.schedule.controller.rest.adminpanel;

import com.vitgon.schedule.annotation.validation.LocaleExists;
import com.vitgon.schedule.dto.UserDtoAdminPanel;
import com.vitgon.schedule.service.UserDtoService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin-panel/translations")
public class UserRestControllerAdminPanel {

	private UserDtoService userDtoService;

	public UserRestControllerAdminPanel(UserDtoService userDtoService) {
		this.userDtoService = userDtoService;
	}

	@GetMapping("/users/role/{role}")
	public List<UserDtoAdminPanel> getAllByRole(
			@PathVariable String role,
			@PageableDefault(page=0, size = 10, sort = {"id"}, direction = Direction.ASC) Pageable pageable) {
		return userDtoService.getUserDtoListByRoleForAdminPanel(role, pageable);
	}
	
	@GetMapping("/users/role/{role}/locale-id/{localeId}")
	public List<UserDtoAdminPanel> getAllByLocaleAndRole(
			@PathVariable String role,
			@PathVariable @LocaleExists Integer localeId,
			@PageableDefault(page=0, size = 10, sort = {"id"}, direction = Direction.ASC) Pageable pageable) {
		return userDtoService.getUserDtoListByRoleAndLocaleIdForAdminPanel(localeId, role, pageable);
	}
}
