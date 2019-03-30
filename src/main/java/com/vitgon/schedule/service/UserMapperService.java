package com.vitgon.schedule.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.vitgon.schedule.dto.TeacherDto;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.service.database.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class UserMapperService {
	
	private UserService userService;
	private UserNameService userNameService;

	public List<TeacherDto> mapToTeacherDTOList(List<User> users) {
		return users.stream()
			.map(user -> {
				TeacherDto teacherDTO = new TeacherDto();
				teacherDTO.setId(user.getId());
				teacherDTO.setName(userNameService.makeupUsername(user));
				return teacherDTO;
			})
			.collect(Collectors.toList());
	}
	
	public Map<Integer, String> mapAllToMap(Locale locale) {
		List<User> users = userService.findAll();
		return users.stream()
			.collect(Collectors.toMap(User::getId, user -> {
				return userNameService.makeupUsername(user, locale);
			}));
	}
}
