package com.vitgon.schedule.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import com.vitgon.schedule.dto.GroupDto;
import com.vitgon.schedule.dto.LocaleDto;
import com.vitgon.schedule.dto.MajorDtoControl;
import com.vitgon.schedule.dto.SchoolDto;
import com.vitgon.schedule.dto.SubjectDto;
import com.vitgon.schedule.dto.TeacherDto;
import com.vitgon.schedule.model.database.Locale;


public class ControlPanelAttributesServiceTest {
	
	private SubjectMapperService subjectMapperService = mock(SubjectMapperService.class);
	private UserDtoService userDTOService = mock(UserDtoService.class);
	private SchoolConverterService schoolConverterService = mock(SchoolConverterService.class);
	private LocaleMapperService localeMapperService = mock(LocaleMapperService.class);
	private MajorConverterService majorConverterService = mock(MajorConverterService.class);
	private GroupMapperService groupMapperService = mock(GroupMapperService.class);
	
	private ControlPanelAttributesService controlPanelAttrService;

	@Before
	public void init() {
		controlPanelAttrService = new ControlPanelAttributesService(
				subjectMapperService,
				userDTOService,
				schoolConverterService,
				localeMapperService,
				majorConverterService,
				groupMapperService);
	}
	
	@Test
	public void testSetDataAttributesMethod() {
		ModelMap modelMap = new ModelMap();
		Locale locale = mock(Locale.class);
		
		when(schoolConverterService.convertToMap(locale)).thenReturn(new HashMap<Integer,String>());
		when(userDTOService.getTeachersDto()).thenReturn(new ArrayList<TeacherDto>());
		when(localeMapperService.mapLocalesToList()).thenReturn(new ArrayList<LocaleDto>());
		when(subjectMapperService.mapToSubjectDTOList()).thenReturn(new ArrayList<SubjectDto>());
		when(schoolConverterService.convertToSchoolDtoList()).thenReturn(new ArrayList<SchoolDto>());
		when(majorConverterService.convertToMajorDtoControlList()).thenReturn(new ArrayList<MajorDtoControl>());
		when(groupMapperService.convertToGroupDtoList()).thenReturn(new ArrayList<GroupDto>());
		
		controlPanelAttrService.setDataAttributes(modelMap, locale);
		
		verify(schoolConverterService, times(1)).convertToMap(locale);
		verify(userDTOService, times(1)).getTeachersDto();
		verify(localeMapperService, times(1)).mapLocalesToList();
		verify(subjectMapperService, times(1)).mapToSubjectDTOList();
		verify(schoolConverterService, times(1)).convertToSchoolDtoControlList();
		verify(majorConverterService, times(1)).convertToMajorDtoControlList();
		verify(groupMapperService, times(1)).convertToGroupDtoList();
		
		assertNotNull(modelMap.get("schools"));
		assertNotNull(modelMap.get("teachers"));
		assertNotNull(modelMap.get("locales"));
		assertNotNull(modelMap.get("subjects"));
		assertNotNull(modelMap.get("schoolDtoList"));
		assertNotNull(modelMap.get("majorDtoList"));
		assertNotNull(modelMap.get("groupDtoList"));
		assertNotNull(modelMap.get("degrees"));
	}
}
