package com.vitgon.schedule.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vitgon.schedule.dto.UserDto;
import com.vitgon.schedule.dto.UserDtoAdminPanel;
import com.vitgon.schedule.projection.ScheduleProjection;
import com.vitgon.schedule.projection.UserProjection;
import com.vitgon.schedule.service.database.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserDtoService {
	
	private UserService userService;
	private LocaleConverterService localeConverterService;
	
	public List<UserDto> getTeachersDto() {
		return getUserDtoListByRole("teacher", PageRequest.of(0, 1000));
	}
	
	public UserDto getUserDtoFromScheduleProjection(ScheduleProjection schedule) {
		UserDto userDto = new UserDto();
		userDto.setId(schedule.getUser_id());
		userDto.setFirstname(schedule.getFirstname());
		userDto.setLastname(schedule.getLastname());
		userDto.setMiddlename(schedule.getMiddlename());
		userDto.setFullName(makeupFullname(schedule.getLastname(), schedule.getFirstname(), schedule.getMiddlename()));
		return userDto;
	}

	public List<UserDto> getUserDtoList(List<UserProjection> users) {
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
	
	public List<UserDto> getUserDtoListByRole(String role, Pageable pageable) {
		Integer localeId = localeConverterService.getClientLocale().getId();
		return getUserDtoListByRoleAndLocaleId(localeId, role, pageable);
	}
	
	public List<UserDto> getUserDtoListByRoleAndLocaleId(Integer localeId, String role, Pageable pageable) {
		Page<UserProjection> users = userService.getAllUsersByLocaleIdAndRoleJoiningWithTranslation(localeId, role, pageable);
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
	
	public List<UserDtoAdminPanel> getUserDtoListByRoleAndLocaleIdForAdminPanel(Integer localeId, String role, Pageable pageable) {
		Page<UserProjection> users = userService.getAllUsersByLocaleIdAndRoleJoiningWithTranslation(localeId, role, pageable);
		return users.stream()
			.map(user -> {
				UserDtoAdminPanel userDto = new UserDtoAdminPanel();
				userDto.setId(user.getId());
				
				userDto.setFirstname(user.getKey_firstname());
				userDto.setLastname(user.getKey_lastname());
				userDto.setMiddlename(user.getKey_middlename());
				userDto.setFullName(makeupFullname(user.getKey_lastname(), user.getKey_firstname(), user.getKey_middlename()));
				
				userDto.setFirstnameTranslation(user.getFirstname());
				userDto.setLastnameTranslation(user.getLastname());
				userDto.setMiddlenameTranslation(user.getMiddlename());
				userDto.setFullNameTranslation(makeupFullname(user.getLastname(), user.getFirstname(), user.getMiddlename()));
				return userDto;
			})
			.collect(Collectors.toList());
	}
	
	public List<UserDtoAdminPanel> getUserDtoListByRoleForAdminPanel(String role, Pageable pageable) {
		Integer localeId = localeConverterService.getClientLocale().getId();
		return getUserDtoListByRoleAndLocaleIdForAdminPanel(localeId, role, pageable);
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
}
