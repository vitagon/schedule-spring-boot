package com.vitgon.schedule.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.vitgon.schedule.dao.SchoolDao;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.School;

@RunWith(MockitoJUnitRunner.class)
public class SchoolControllerTest {
	
	@InjectMocks
	SchoolController schoolController = new SchoolController();
	
	@Mock
	private SchoolDao schoolService;
	
	@Before
	public void setUp() {
		School school = new School(1, "test_school");
		List<School> schools = Arrays.asList(school);
	    when(schoolService.findAll()).thenReturn(schools);
	}

	@Test
	public void testReturnValueIsListOfSchools() {
		Map<School, Map<String,Object>> returnedValue = schoolController.getSchools();
		assertEquals("test_school", returnedValue.get(0).get("url"));
	}
	
	@Test
	public void testFindAllMethodWasInvoked() {
		schoolController.getSchools();
		Locale locale = new Locale();
		verify(schoolService, times(1)).findAllByLocale(locale);
	}
}
