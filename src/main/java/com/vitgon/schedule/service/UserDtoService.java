package com.vitgon.schedule.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vitgon.schedule.dto.UserDto;
import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.service.database.UserService;

@Service
public class UserDtoService {
	
	private UserService userService;

	public UserDtoService(UserService userService) {
		this.userService = userService;
	}
	
	public List<UserDto> getUserDtoListByRole(String role, Pageable pageable) {
		Page<User> users = userService.findByRole(role.toUpperCase(), pageable);
		return users.stream()
			.map(user -> {
				UserDto userDto = new UserDto();
				userDto.setId(user.getId());
				userDto.setFirstname(user.getFirstname());
				userDto.setLastname(user.getLastname());
				userDto.setMiddlename(user.getMiddlename());
				userDto.setFullName(makeupFullname(user.getLastname(), user.getFirstname(), user.getMiddlename()));
				return userDto;
			})
			.collect(Collectors.toList());
	}
	
	public String makeupFullname(String lastname, String firstname, String middlename) {
		if (lastname == null || lastname.length() < 1 || firstname == null || firstname.length() < 1) {
			return null;
		}
		
		StringBuilder nameStringBuilder = new StringBuilder()
				.append(capitalizeFirstLetter(lastname))
				.append(" ")
				.append(capitalizeFirstLetter(firstname));
		
		if (middlename != null && middlename.length() > 1) {
			nameStringBuilder
				.append(" ")
				.append(capitalizeFirstLetter(middlename));
		}

		return nameStringBuilder.toString();
	}
	
	public String capitalizeFirstLetter(String word) {
		return word.substring(0,1).toUpperCase() + word.substring(1);
	}

	public List<UserDto> getUserDtoListByRole(String role) {
		List<User> users = userService.findByRole(role.toUpperCase());
		return users.stream()
			.map(user -> {
				UserDto userDto = new UserDto();
				userDto.setId(user.getId());
				userDto.setFirstname(user.getFirstname());
				userDto.setLastname(user.getLastname());
				userDto.setMiddlename(user.getMiddlename());
				userDto.setFullName(makeupFullname(user.getLastname(), user.getFirstname(), user.getMiddlename()));
				return userDto;
			})
			.collect(Collectors.toList());
	}
}
