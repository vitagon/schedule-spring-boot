package com.vitgon.schedule.controller.rest;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.School;
import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.service.database.SchoolService;

import static org.mockito.Mockito.mock;

public class SchoolRestControllerTest {
	
	private SchoolService schoolService = mock(SchoolService.class);
	private LocaleService localeService = mock(LocaleService.class);
	
	private SchoolRestController schoolRestController = new SchoolRestController(schoolService, localeService);
	
	@Test
	public void getSchoolsTest() throws Exception {
		Locale locale = new Locale("en");
		BDDMockito.given(localeService.findByCode(ArgumentMatchers.anyString())).willReturn(locale);
		BDDMockito.given(schoolService.findAllByLocale(locale)).willReturn(Arrays.asList(new School(1, "management")));
		
		List<School> schools = schoolRestController.getSchools();
		System.out.println(schools);
		
		Mockito.verify(localeService, Mockito.times(1)).findByCode(ArgumentMatchers.anyString());
		Assert.assertThat(schools.toString(),
				CoreMatchers.is(Arrays.asList(new School(1, "management")).toString()));
	}
}
