package com.vitgon.schedule.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.model.database.translation.SubjectTranslation;
import com.vitgon.schedule.service.database.translation.SubjectTranslationService;

public class SubjectTitleServiceTest {

	private SubjectTranslationService subjectTranslationService;
	private SubjectTitleService subjectTitleService;
	
	@Before
	public void init() {
		subjectTranslationService = mock(SubjectTranslationService.class);
		subjectTitleService = new SubjectTitleService(subjectTranslationService);
	}
	
	@Test
	public void testGetSubjectTitleMethod_whenLocaleIsNotENAndSubjectTranslationExists() {
		Locale locale = new Locale("ru");
		Subject subject = createSubject();
		SubjectTranslation translation = createSubjectTranslation();
		
		when(subjectTranslationService.findByLocaleAndSubject(locale, subject)).thenReturn(translation);
		String subjectTitle = subjectTitleService.getSubjectTitle(locale, subject);
		
		Assert.assertEquals("Высшая математика", subjectTitle);
		verify(subjectTranslationService).findByLocaleAndSubject(locale, subject);
	}
	
	@Test
	public void testGetSubjectTitleMethod_whenLocaleIsNotENAndSubjectTranslationDoesNotExist() {
		Locale locale = new Locale("ru");
		Subject subject = createSubject();
		
		when(subjectTranslationService.findByLocaleAndSubject(locale, subject)).thenReturn(null);
		String subjectTitle = subjectTitleService.getSubjectTitle(locale, subject);
		
		Assert.assertEquals("Math", subjectTitle);
		verify(subjectTranslationService).findByLocaleAndSubject(locale, subject);
	}
	
	@Test
	public void testGetSubjectTitleMethod_whenLocaleIsEN() {
		Locale locale = new Locale("en");
		Subject subject = createSubject();
		
		String subjectTitle = subjectTitleService.getSubjectTitle(locale, subject);
		Assert.assertEquals("Math", subjectTitle);
		verify(subjectTranslationService, never()).findByLocaleAndSubject(locale, subject);
	}
	
	private SubjectTranslation createSubjectTranslation() {
		SubjectTranslation translation = new SubjectTranslation();
		translation.setTitle("высшая математика");
		return translation;
	}
	
	private Subject createSubject() {
		Subject subject = new Subject();
		subject.setName("math");
		return subject;
	}
}