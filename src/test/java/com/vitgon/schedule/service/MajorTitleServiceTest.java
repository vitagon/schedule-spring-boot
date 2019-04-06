package com.vitgon.schedule.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.model.database.translation.MajorTranslation;
import com.vitgon.schedule.service.database.translation.MajorTranslationService;

public class MajorTitleServiceTest {

	private MajorTranslationService majorTranslationService;
	private MajorTitleService majorTitleService;
	
	@Before
	public void init() {
		majorTranslationService = mock(MajorTranslationService.class);
		majorTitleService = new MajorTitleService(majorTranslationService);
	}
	
	@Test
	public void testGetMajorTitleWith_whenLocaleIsNotENAndMajorTranslationExists() {
		Locale locale = new Locale("ru");
		Major major = createMajor();
		MajorTranslation translation = createMajorTranslation();
		
		when(majorTranslationService.findByLocaleAndMajor(locale, major)).thenReturn(translation);
		String majorTitle = majorTitleService.getMajorTitle(locale, major);
		Assert.assertEquals("Прикладная информатика", majorTitle);
		verify(majorTranslationService).findByLocaleAndMajor(locale, major);
	}
	
	@Test
	public void testGetMajorTitleWith_whenLocaleIsNotENAndMajorTranslationDoesNotExist() {
		Locale locale = new Locale("ru");
		Major major = createMajor();
		
		when(majorTranslationService.findByLocaleAndMajor(locale, major)).thenReturn(null);
		String majorTitle = majorTitleService.getMajorTitle(locale, major);
		Assert.assertEquals("Applied computer science", majorTitle);
		verify(majorTranslationService).findByLocaleAndMajor(locale, major);
	}
	
	@Test
	public void testGetMajorTitleWith_whenLocaleIsEN() {
		Locale locale = new Locale("en");
		Major major = createMajor();
		
		String majorTitle = majorTitleService.getMajorTitle(locale, major);
		Assert.assertEquals("Applied computer science", majorTitle);
		verify(majorTranslationService, never()).findByLocaleAndMajor(locale, major);
	}
	
	private MajorTranslation createMajorTranslation() {
		MajorTranslation translation = new MajorTranslation();
		translation.setTitle("прикладная информатика");
		return translation;
	}

	private Major createMajor() {
		Major major = new Major();
		major.setName("applied computer science");
		return major;
	}
}
