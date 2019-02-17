package com.vitgon.schedule.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vitgon.schedule.dto.TeacherDTO;
import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.service.database.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserDTOService {
	
	private UserService userService;
	private UserMapperService userMapperService;

	public List<TeacherDTO> getTeachersDTO() {
		List<User> users = userService.findBySpecificRoles(Arrays.asList("teacher".toUpperCase()));
		List<TeacherDTO> teachers = userMapperService.mapToTeacherDTOList(users);
		return teachers;
	}
}
