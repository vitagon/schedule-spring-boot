package com.vitgon.schedule.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vitgon.schedule.dto.UserDto;
import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.service.database.UserService;

public class UserDtoServiceTest {
	
//	private UserService userService;
//	private UserMapperService userMapperService;
//	private UserDtoService userDtoService;
//
//	@Before
//	public void init() {
//		userService = mock(UserService.class);
//		userMapperService = mock(UserMapperService.class);
//		userDtoService = new UserDtoService(userService, userMapperService);
//	}
//	
//	@Test
//	public void testGetTeachersDto() {
//		List<User> users = Arrays.asList(createUser());
//		List<UserDto> teacherDtoList = Arrays.asList(createTeacherDto());
//		
//		when(userService.findBySpecificRoles(Arrays.asList("TEACHER"))).thenReturn(users);
//		when(userMapperService.mapToTeacherDTOList(users)).thenReturn(teacherDtoList);
//		
//		List<UserDto> teacherDtoListResult = userDtoService.getTeachersDto();
//		Assert.assertEquals(56, teacherDtoListResult.get(0).getId());
//		Assert.assertEquals("James", teacherDtoListResult.get(0).getFirstname());
//		verify(userService).findBySpecificRoles(Arrays.asList("TEACHER"));
//		verify(userMapperService).mapToTeacherDTOList(users);
//	}

	private UserDto createTeacherDto() {
		UserDto teacherDto = new UserDto();
		teacherDto.setId(56);
		teacherDto.setFirstname("James");;
		return teacherDto;
	}

	private User createUser() {
		User user = new User();
		user.setKeyFirstname("James");
		user.setKeyLastname("Gosling");
		return user;
	}
}
