package com.vitgon.schedule.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vitgon.schedule.dto.TeacherDTO;
import com.vitgon.schedule.model.database.auth.User;

@Component
public class UserMapperService {
	
	private UserNameService userNameService;

	@Autowired
	public UserMapperService(UserNameService userNameService) {
		this.userNameService = userNameService;
	}

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
}
