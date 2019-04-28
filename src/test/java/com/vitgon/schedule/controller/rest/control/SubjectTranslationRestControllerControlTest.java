package com.vitgon.schedule.controller.rest.control;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.vitgon.schedule.dto.AddSubjectTranslationDto;
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.model.database.translation.SubjectTranslation;
import com.vitgon.schedule.model.database.translation.pk.SubjectTranslationId;
import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.service.database.SubjectService;
import com.vitgon.schedule.service.database.translation.SubjectTranslationService;

@RunWith(MockitoJUnitRunner.class)
public class SubjectTranslationRestControllerControlTest {
	
	@Mock
	private SubjectService subjectService;
	
	@Mock
	private LocaleService localeService;
	
	@Mock
	private SubjectTranslationService subjectTranslationService;
	
	@InjectMocks
	private SubjectTranslationRestControllerControl subjectTranslationRestControllerControl;
	
	@Test
	public void addSubjectTranslation_AddSubjectTranslationDto__success() {
		AddSubjectTranslationDto addSubjectTranslationDto = new AddSubjectTranslationDto();
		addSubjectTranslationDto.setSubjectId(43);
		addSubjectTranslationDto.setLocaleId(12);
		addSubjectTranslationDto.setTitle("налоги");;
		Subject subject = new Subject();
		Locale locale = new Locale();
		
		when(subjectService.findById(43)).thenReturn(subject);
		when(localeService.findById(12)).thenReturn(locale);
		
		ApiSuccess apiSuccess = subjectTranslationRestControllerControl.addSubjectTranslation(addSubjectTranslationDto);
		assertNotNull(apiSuccess);
		assertFalse(apiSuccess.getMessage().isEmpty());
		verify(subjectService, times(1)).findById(43);
		verify(localeService, times(1)).findById(12);
		verify(subjectTranslationService, times(1)).save(new SubjectTranslation(
				new SubjectTranslationId(subject, locale),
				addSubjectTranslationDto.getTitle()
		));
	}
}
