package com.vitgon.schedule.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vitgon.schedule.dto.TeacherDto;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.service.database.UserService;

public class UserMapperServiceTest {
	
	private UserService userService;
	private UserNameService userNameService;
	private UserMapperService userMapperService;

	@Before
	public void init() {
		userService = mock(UserService.class);
		userNameService = mock(UserNameService.class);
		userMapperService = new UserMapperService(userService, userNameService);
	}
	
	@Test
	public void testMapToTeacherDTOListMethodWithListOfUsers() {
		User user = createUser();
		
		when(userNameService.makeupUsername(user)).thenReturn("James Gosling");
		List<TeacherDto> teacherDtoList = userMapperService.mapToTeacherDTOList(Arrays.asList(user));
		
		Assert.assertEquals(554, teacherDtoList.get(0).getId());
		Assert.assertEquals("James Gosling", teacherDtoList.get(0).getName());
		verify(userNameService).makeupUsername(user);
	}
	
	@Test
	public void testMapAllToMapMethodWithLocale() {
		Locale locale = new Locale("en");
		User user = createUser();
		
		when(userService.findAll()).thenReturn(Arrays.asList(user));
		when(userNameService.makeupUsername(user, locale)).thenReturn("James Gosling");
		Map<Integer, String> teachersMap = userMapperService.mapAllToMap(locale);
		
		Assert.assertTrue(teachersMap.containsKey(554));
		Assert.assertEquals("James Gosling", teachersMap.get(554));
		
		verify(userNameService).makeupUsername(user, locale);
	}
	
	private User createUser() {
		User user = new User();
		user.setId(554);
		return user;
	}
}
