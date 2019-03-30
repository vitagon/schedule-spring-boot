package com.vitgon.schedule.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.vitgon.schedule.model.database.Locale;

public class EditScheduleViewServiceTest {

	private ScheduleViewService scheduleViewService;
	private SubjectMapperService subjectMapperService;
	private UserMapperService userMapperService;
	private EditScheduleViewService editScheduleViewService;
	
	@Before
	public void init() {
		scheduleViewService = mock(ScheduleViewService.class);
		subjectMapperService = mock(SubjectMapperService.class);
		userMapperService = mock(UserMapperService.class);
		editScheduleViewService = new EditScheduleViewService(scheduleViewService, subjectMapperService, userMapperService);
	}
	
	@Test
	public void testAddEditScheduleViewVarsMethod() {
		Locale locale = new Locale("en");
		ModelAndView modelAndView = new ModelAndView();
		int groupId = 6;
		Map<Integer, String> usersMap = createUsersMap();
		Map<Integer, String> subjectsMap = createSubjectsMap();
		
		doNothing().when(scheduleViewService).setScheduleViewVars(locale, modelAndView, groupId);
		when(userMapperService.mapAllToMap(locale)).thenReturn(usersMap);
		when(subjectMapperService.mapToMap(locale, true)).thenReturn(subjectsMap);
		
		editScheduleViewService.addEditScheduleViewVars(locale, modelAndView, groupId);
		
		ModelMap modelMap = modelAndView.getModelMap();
		Assert.assertEquals(usersMap, modelMap.get("teachersNames"));
		Assert.assertEquals(subjectsMap, modelMap.get("subjects"));
		
		verify(scheduleViewService).setScheduleViewVars(locale, modelAndView, groupId);
		verify(userMapperService).mapAllToMap(locale);
		verify(subjectMapperService).mapToMap(locale, true);
	}

	private Map<Integer, String> createSubjectsMap() {
		Map<Integer, String> subjectsMap = new HashMap<>();
		subjectsMap.put(823, "math");
		return subjectsMap;
	}

	private Map<Integer, String> createUsersMap() {
		Map<Integer, String> usersMap = new HashMap<>();
		usersMap.put(42, "James Gosling");
		return null;
	}
}
