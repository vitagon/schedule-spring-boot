package com.vitgon.schedule.controller.rest.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.vitgon.schedule.dto.MajorDtoControl;
import com.vitgon.schedule.service.MajorConverterService;

@RunWith(MockitoJUnitRunner.class)
public class MajorsRestControllerControlTest {

	@Mock
	private MajorConverterService majorConverterService;
	
	@InjectMocks
	private MajorsRestControllerControl majorsViewRestControllerControl;
	
	@Test
	public void getSchoolsView_LocaleId__whenLocaleIdIsMoreThanZero_thenSuccess() {
//		int localeId = 4;
//		List<MajorDtoControl> majorDtoControlList = new ArrayList<>();
//		when(majorConverterService.convertToMajorDtoControlList(localeId)).thenReturn(majorDtoControlList);
//		
//		ModelAndView modelAndView = majorsViewRestControllerControl.getSchoolsView(localeId);
//		ModelMap modelMap = modelAndView.getModelMap();
//		assertNotNull(modelMap.get("majorDtoList"));
//		assertEquals(majorDtoControlList, modelMap.get("majorDtoList"));
//		verify(majorConverterService, times(1)).convertToMajorDtoControlList(localeId);
	}
	
	@Test
	public void getSchoolsView_LocaleId__whenLocaleIdEqualsToZero_thenSuccess() {
//		int localeId = 0;
//		List<MajorDtoControl> majorDtoControlList = new ArrayList<>();
//		when(majorConverterService.convertToMajorDtoControlList()).thenReturn(majorDtoControlList);
//		
//		ModelAndView modelAndView = majorsViewRestControllerControl.getSchoolsView(localeId);
//		ModelMap modelMap = modelAndView.getModelMap();
//		assertNotNull(modelMap.get("majorDtoList"));
//		assertEquals(majorDtoControlList, modelMap.get("majorDtoList"));
//		verify(majorConverterService, times(1)).convertToMajorDtoControlList();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getSchoolsView_LocaleId__whenLocaleIdIsLessThanZero_thenThrowException() {
		majorsViewRestControllerControl.getMajors(-1);
	}
}
