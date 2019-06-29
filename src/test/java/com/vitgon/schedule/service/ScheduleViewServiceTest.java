package com.vitgon.schedule.service;

import com.vitgon.schedule.service.database.GroupService;
import com.vitgon.schedule.service.database.ScheduleService;


public class ScheduleViewServiceTest {
	
	private ScheduleService scheduleService;
	private GroupService groupService;
	private ScheduleDtoService scheduleTreeService;
	private ScheduleViewService scheduleViewService;

//	@Before
//	public void init() {
//		scheduleService = mock(ScheduleService.class);
//		groupService = mock(GroupService.class);
//		scheduleTreeService = mock(ScheduleTreeService.class);
//		scheduleViewService = new ScheduleViewService(scheduleService, groupService, scheduleTreeService);
//	}
	
//	@Test
//	public void testSetScheduleViewVarsMethod() throws Exception {
//		Locale locale = Mockito.mock(Locale.class);
//		ModelAndView modelAndView = new ModelAndView();
//		Integer groupId = 1;
//		
//		Group group = new Group();
//		List<Schedule> schedules = Arrays.asList(new Schedule());
//		when(groupService.findById(groupId)).thenReturn(group);
//		when(scheduleService.findByGroup(group)).thenReturn(schedules);
//		when(scheduleTreeService.getScheduleTree(schedules, locale)).thenReturn(new ScheduleTree());
//		
//		scheduleViewService.setScheduleViewVars(locale, modelAndView, groupId);
//		ModelMap modelMap = modelAndView.getModelMap();
//		Assert.assertNotNull(modelMap.get("days"));
//		Assert.assertNotNull(modelMap.get("bells"));
//		Assert.assertNotNull(modelMap.get("schedules"));
//		Assert.assertNotNull(modelMap.get("groupId"));
//		
//		verify(groupService, times(1)).findById(groupId);
//		verify(scheduleService, times(1)).findByGroup(group);
//		verify(scheduleTreeService, times(1)).getScheduleTree(schedules, locale);
//	}
}
