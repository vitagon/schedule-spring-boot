package com.vitgon.schedule.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.vitgon.schedule.controller.rest.SchoolRestController;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.service.database.SchoolService;

@RunWith(MockitoJUnitRunner.class)
public class SchoolControllerTest {
	
	@Mock
	private SchoolService schoolService;
	
	@Mock
	private LocaleService localeService;
	
	@InjectMocks
	SchoolRestController schoolController;
	
	@Before
	public void setUp() {
		School school = new School(1, "test_school");
		List<School> schools = Arrays.asList(school);
	    
	    when(localeService.findByCode(ArgumentMatchers.anyString())).thenReturn(new Locale());
		when(schoolService.findAllByLocale(ArgumentMatchers.any(Locale.class)))
			.thenReturn(schools);
	}

	@Test
	public void testReturnValueIsListOfSchools() {
		List<School> returnedValue = schoolController.getSchools();
		assertEquals("test_school", returnedValue.get(0).getUrl());
	}
	
	@Test
	public void testFindAllMethodWasInvoked() {
		schoolController.getSchools();
		verify(schoolService, times(1)).findAllByLocale(ArgumentMatchers.any(Locale.class));
	}
}
