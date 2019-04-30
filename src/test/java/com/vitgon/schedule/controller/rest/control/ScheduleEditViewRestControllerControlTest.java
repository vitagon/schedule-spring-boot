package com.vitgon.schedule.controller.rest.control;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.hamcrest.CoreMatchers;
import org.hamcrest.text.IsEmptyString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.service.EditScheduleViewService;
import com.vitgon.schedule.service.LocaleConverterService;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleEditViewRestControllerControlTest {

	@Mock
	private LocaleConverterService localeConverterService;
	
	@Mock
	private EditScheduleViewService editScheduleViewService;
	
	@InjectMocks
	private ScheduleEditViewRestControllerControl scheduleEditViewRestControllerControl;
	
	@Test
	public void getScheduleEditViewFragment_HttpServletRequest_GroupId__success() {
		int groupId = 4;
		HttpServletRequest request = mock(HttpServletRequest.class);
		Locale locale = new Locale();
		
		when(localeConverterService.getClientLocale(request)).thenReturn(locale);
		ModelAndView modelAndView = scheduleEditViewRestControllerControl.getScheduleEditViewFragment(request, groupId);
		
		assertThat(modelAndView.getViewName(), CoreMatchers.not(IsEmptyString.isEmptyOrNullString()));
		verify(localeConverterService, times(1)).getClientLocale(request);
		verify(editScheduleViewService, times(1)).addEditScheduleViewVars(locale, modelAndView, groupId);
	}
}
