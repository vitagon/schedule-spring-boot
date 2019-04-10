package com.vitgon.schedule.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.model.database.translation.SchoolTranslation;
import com.vitgon.schedule.service.database.translation.SchoolTranslationService;

public class SchoolTitleServiceTest {
	
	private SchoolTranslationService schoolTranslationService;
	private SchoolTitleService schoolTitleService;
	
	@Before
	public void init() {
		schoolTranslationService = mock(SchoolTranslationService.class);
		schoolTitleService = new SchoolTitleService(schoolTranslationService);
	}
	
	@Test
	public void testGetSchoolTitleMethod_whenLocaleIsNotENAndSchoolTranslationExists() {
		Locale locale = new Locale("ru");
		School school = createSchool();
		SchoolTranslation translation = createSchoolTranslation();
		
		when(schoolTranslationService.findByLocaleAndSchool(locale, school)).thenReturn(translation);
		String schoolTitle = schoolTitleService.getSchoolTitle(locale, school);
		
		Assert.assertEquals("Школа естественных наук", schoolTitle);
		verify(schoolTranslationService).findByLocaleAndSchool(locale, school);
	}
	
	@Test
	public void testGetSchoolTitleMethod_whenLocaleIsNotENAndSchoolTranslationDoesNotExist() {
		Locale locale = new Locale("ru");
		School school = createSchool();
		
		when(schoolTranslationService.findByLocaleAndSchool(locale, school)).thenReturn(null);
		String schoolTitle = schoolTitleService.getSchoolTitle(locale, school);
		
		Assert.assertEquals("School of natural science", schoolTitle);
		verify(schoolTranslationService).findByLocaleAndSchool(locale, school);
	}
	
	@Test
	public void testGetSchoolTitleMethod_whenLocaleIsEN() {
		Locale locale = new Locale("en");
		School school = createSchool();
		
		String schoolTitle = schoolTitleService.getSchoolTitle(locale, school);
		Assert.assertEquals("School of natural science", schoolTitle);
		verify(schoolTranslationService, never()).findByLocaleAndSchool(locale, school);
	}
	
	private SchoolTranslation createSchoolTranslation() {
		SchoolTranslation translation = new SchoolTranslation();
		translation.setTitle("школа естественных наук");
		return translation;
	}
	
	private School createSchool() {
		School school = new School();
		school.setName("school of natural science");
		return school;
	}
}
