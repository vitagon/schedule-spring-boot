package com.vitgon.schedule.controller.rest;

import com.vitgon.schedule.dao.auth.UserDao;
import com.vitgon.schedule.dto.UserDto;
import com.vitgon.schedule.model.database.auth.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserSearchRestController {
	
	private UserDao userDao;

	public UserSearchRestController(UserDao userDao) {
		this.userDao = userDao;
	}

	@Transactional
	@GetMapping("/users/search")
	public List<UserDto> searchUser(
			@RequestParam(value = "lastname", required = false) String lastname,
			@RequestParam(value = "firstname", required = false) String firstname,
			@RequestParam(value = "middlename", required = false) String middlename) {
		
		List<UserDto> userDtoList = new ArrayList<>();
		if ((lastname != null && !lastname.equals("")) &&
			(firstname != null && !firstname.equals("")) &&
			(middlename != null && !middlename.equals(""))) {
			List<User> users = userDao.findByLastnameAndFirstnameAndMiddlename(
					lastname.toLowerCase(),
					firstname.toLowerCase(),
					middlename.toLowerCase()
			);
			userDtoList = convertToDto(users);
		}
		
		if ((lastname != null && !lastname.equals("")) &&
			(firstname == null || firstname.equals("")) &&
			(middlename == null || middlename.equals(""))) {
			List<User> users = userDao.findByLastname(lastname.toLowerCase());
			userDtoList = convertToDto(users);
		}
		
		if (lastname != null && !lastname.equals("") &&
			(firstname != null && !firstname.equals("")) &&
			(middlename == null || middlename.equals(""))) {
			List<User> users = userDao.findByLastnameAndFirstname(
					lastname.toLowerCase(),
					firstname.toLowerCase()
			);
			userDtoList = convertToDto(users);
		}
		
		if ((lastname == null || lastname.equals("")) &&
			(firstname != null && !firstname.equals("")) &&
			(middlename != null && !middlename.equals(""))) {
			List<User> users = userDao.findByFirstnameAndMiddlename(
					firstname.toLowerCase(),
					middlename.toLowerCase()
			);
			userDtoList = convertToDto(users);
		}
		
		return userDtoList;
	}
	
	private List<UserDto> convertToDto(List<User> users) {
		return users.stream()
			.map(user -> {
				UserDto userDto = new UserDto();
				userDto.setId(user.getId());
				userDto.setFirstname(user.getFirstname());
				userDto.setLastname(user.getLastname());
				userDto.setMiddlename(user.getMiddlename());
				return userDto;
			})
			.collect(Collectors.toList());
	}
}
