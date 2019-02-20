package com.vitgon.schedule.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.vitgon.schedule.dto.TeacherDTO;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.service.database.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class UserMapperService {
	
	private UserService userService;
	private UserNameService userNameService;

	public List<TeacherDTO> mapToTeacherDTOList(List<User> users) {
		return users.stream()
			.map(user -> {
				TeacherDTO teacherDTO = new TeacherDTO();
				teacherDTO.setId(user.getId());
				teacherDTO.setName(userNameService.makeupUsername(user));
				return teacherDTO;
			})
			.collect(Collectors.toList());
	}
	
	public Map<Integer, String> mapUsersToMap(Locale locale) {
		List<User> users = userService.findAll();
		Map<Integer, String> teachersNames = new HashMap<>();
		for (User teacher : users) {
			teachersNames.put(teacher.getId(), userNameService.makeupUsername(teacher, locale));
		}
		return teachersNames;
	}
}
