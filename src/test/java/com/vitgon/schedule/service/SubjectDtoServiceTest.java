package com.vitgon.schedule.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.vitgon.schedule.dto.SubjectDto;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.service.database.SubjectService;
import com.vitgon.schedule.service.database.translation.SubjectTranslationService;

public class SubjectDtoServiceTest {
	
	private SubjectService subjectService;
	private SubjectTranslationService subjectTranslService;
	private SubjectDtoService subjectMapperService;

//	@Before
//	public void init() {
//		subjectService = mock(SubjectService.class);
//		subjectTranslService = mock(SubjectTranslationService.class);
//		subjectMapperService = spy(new SubjectDtoService(subjectService, subjectTranslService));
//	}
//	
//	@Test
//	public void testMapToSubjectDTOList() {
//		List<Subject> subjects = createSubjects();
//		List<SubjectDto> subjectDtoList = createSubjectDtoList();
//		when(subjectService.findAll()).thenReturn(subjects);
//		when(subjectMapperService.mapToSubjectDtoList(subjects)).thenReturn(subjectDtoList);
//		
//		List<SubjectDto> resultList = subjectMapperService.mapToSubjectDTOList();
//		
//		Assert.assertEquals(689, resultList.get(0).getId());
//		
//		verify(subjectService).findAll();
//		verify(subjectMapperService).mapToSubjectDtoList(subjects);
//	}
//	
//	@Test
//	public void testMapToSubjectDTOListWithListOfSubjects() {
//		List<Subject> subjects = createSubjects();
//		List<SubjectDto> resultList = subjectMapperService.mapToSubjectDtoList(subjects);
//		
//		Assert.assertEquals(689, resultList.get(0).getId());
//		Assert.assertEquals("math", resultList.get(0).getName());
//	}
//	
//	@Test
//	public void testMapToSubjectDTOListWithListOfSubjectsAndLocaleAndSubstituteNull() {
//		Locale locale = new Locale("ru");
//		List<Subject> subjects = createSubjects();
//		boolean substituteNull = true;
//		
//		when(subjectTranslService.getSubjectTitle(subjects.get(0), locale, substituteNull))
//			.thenReturn("высшая математика");
//		
//		List<SubjectDto> resultList = subjectMapperService.mapToSubjectDtoList(subjects, locale, substituteNull);
//		
//		Assert.assertEquals(689, resultList.get(0).getId());
//		Assert.assertEquals("math", resultList.get(0).getName());
//		Assert.assertEquals("высшая математика", resultList.get(0).getTranslation());
//		
//		verify(subjectTranslService).getSubjectTitle(subjects.get(0), locale, substituteNull);
//	}
//	
//	@Test
//	public void testMapToMap() {
//		Locale locale = new Locale("ru");
//		List<Subject> subjects = createSubjects();
//		boolean substituteNull = true;
//		
//		when(subjectService.findAll()).thenReturn(subjects);
//		when(subjectTranslService.getSubjectTitle(subjects.get(0), locale, substituteNull))
//			.thenReturn("высшая математика");
//		
//		Map<Integer, String> resultMap = subjectMapperService.getSubjectDtoList(locale, substituteNull);
//		
//		Assert.assertTrue(resultMap.containsKey(689));
//		Assert.assertEquals("высшая математика", resultMap.get(689));
//		
//		verify(subjectService).findAll();
//		verify(subjectTranslService).getSubjectTitle(subjects.get(0), locale, substituteNull);
//	}
//	
//	@Test
//	public void testMapToMapWhenNoSubjectsExist() {
//		Locale locale = new Locale("ru");
//		boolean substituteNull = true;
//		
//		when(subjectService.findAll()).thenReturn(new ArrayList<>());
//		Map<Integer, String> resultMap = subjectMapperService.getSubjectDtoList(locale, substituteNull);
//		
//		Assert.assertTrue(resultMap.isEmpty());
//		verify(subjectService).findAll();
//		verify(subjectTranslService, Mockito.never()).getSubjectTitle(null, locale, substituteNull);
//	}
	
	private List<SubjectDto> createSubjectDtoList() {
		SubjectDto subjectDto = new SubjectDto();
		subjectDto.setId(689);
		subjectDto.setName("math");
		return Arrays.asList(subjectDto);
	}

	private List<Subject> createSubjects() {
		Subject subject = new Subject();
		subject.setId(689);
		subject.setName("math");
		return Arrays.asList(subject);
	}
}
