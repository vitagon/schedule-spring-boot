package com.vitgon.schedule.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.vitgon.schedule.dto.SchoolDto;
import com.vitgon.schedule.dto.SchoolDtoControl;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.service.database.SchoolService;

@RunWith(MockitoJUnitRunner.class)
public class SchoolConverterServiceTest {
	
	@Mock
	private SchoolService schoolService;
	
	@Mock
	private SchoolTitleService schoolTitleService;
	
	@Mock
	private MajorTitleService majorTitleService;
	
	@InjectMocks
	private SchoolConverterService schoolConverterService;
	
	@Test
	public void convertToSchoolDtoList_void_success() {
		List<School> schools = schools();
		int schoolId = schools.get(0).getId();
		String schoolTitle = schools.get(0).getName();
		
		BDDMockito.when(schoolService.findAll()).thenReturn(schools);
		List<SchoolDto> schoolDtoList = schoolConverterService.convertToSchoolDtoList();
		Assert.assertEquals(schoolId, schoolDtoList.get(0).getId());
		Assert.assertEquals(schoolTitle, schoolDtoList.get(0).getName());
		verify(schoolService, times(1)).findAll();
	}

	@Test
	public void convertToSchoolDtoList_Locale_success() {
		Locale locale = Mockito.mock(Locale.class);
		List<School> schools = schools();
		int schoolId = schools.get(0).getId();
		String schoolTitle = schools.get(0).getName();
		
		BDDMockito.when(schoolService.findAll()).thenReturn(schools);
		BDDMockito.when(schoolTitleService.getSchoolTitle(any(Locale.class), any(School.class)))
				.thenReturn(schoolTitle);
		List<SchoolDto> schoolDtoList = schoolConverterService.convertToSchoolDtoList(locale);
		Assert.assertEquals(schoolId, schoolDtoList.get(0).getId());
		Assert.assertEquals(schoolTitle, schoolDtoList.get(0).getName());
		verify(schoolService, times(1)).findAll();
		verify(schoolTitleService, times(1)).getSchoolTitle(ArgumentMatchers.any(Locale.class), ArgumentMatchers.any(School.class));
	}
	
	@Test
	public void convertToMap_Locale_success() {
		Locale locale = Mockito.mock(Locale.class);
		List<School> schools = schools();
		
		int schoolId = schools.get(0).getId();
		String schoolTitle = schools.get(0).getName();
		
		BDDMockito.when(schoolService.findAll()).thenReturn(schools);
		BDDMockito.when(schoolTitleService.getSchoolTitle(any(Locale.class), any(School.class)))
			.thenReturn(schoolTitle);
		Map<Integer, String> schoolsMap = schoolConverterService.convertToMap(locale);
		
		Assert.assertTrue(schoolsMap.containsKey(schoolId));
		Assert.assertEquals(schoolTitle, schoolsMap.get(schoolId));
		verify(schoolService, times(1)).findAll();
		verify(schoolTitleService, times(1)).getSchoolTitle(any(Locale.class), any(School.class));
	}
	
	@Test
	public void convertToSchoolDtoList_ListOfSchools_Locale_whenMajorExists_success() {
		Locale locale = Mockito.mock(Locale.class);
		List<School> schools = schoolsWithMajors();
		int schoolId = schools.get(0).getId();
		String schoolName = schools.get(0).getName();
		String majorName = schools.get(0).getMajors().get(0).getName();
		
		when(schoolTitleService.getSchoolTitle(any(Locale.class), any(School.class)))
			.thenReturn(schoolName);
		when(majorTitleService.getMajorTitle(any(Locale.class), any(Major.class)))
			.thenReturn(majorName);
		List<SchoolDto> schoolDtoList = schoolConverterService.convertToSchoolDtoList(schools, locale);
		Assert.assertEquals(schoolId, schoolDtoList.get(0).getId());
		Assert.assertEquals(schoolName, schoolDtoList.get(0).getName());
		Assert.assertEquals(majorName, schoolDtoList.get(0).getMajors().get(0).getName());
		
		verify(schoolTitleService, times(1)).getSchoolTitle(any(Locale.class), any(School.class));
		verify(majorTitleService, times(1)).getMajorTitle(any(Locale.class), any(Major.class));
	}
	
	@Test
	public void convertToSchoolDtoList_ListOfSchools_Locale_whenMajorIsNull_success() {
		Locale locale = Mockito.mock(Locale.class);
		List<School> schools = schoolsWithoutMajors();
		int schoolId = schools.get(0).getId();
		String schoolName = schools.get(0).getName();
		
		when(schoolTitleService.getSchoolTitle(any(Locale.class), any(School.class)))
			.thenReturn(schoolName);
		List<SchoolDto> schoolDtoList = schoolConverterService.convertToSchoolDtoList(schools, locale);
		
		Assert.assertEquals(schoolId, schoolDtoList.get(0).getId());
		Assert.assertEquals(schoolName, schoolDtoList.get(0).getName());
		verify(schoolTitleService, times(1)).getSchoolTitle(any(Locale.class), any(School.class));
		verify(majorTitleService, never()).getMajorTitle(any(Locale.class), any(Major.class));
	}
	
	@Test
	public void convertToSchoolDtoControlList_void_ShouldReturnEmptySchoolsDTOControlList() {
		when(schoolService.findAll()).thenReturn(new ArrayList<>());
		List<SchoolDtoControl> schoolDtoControlList = schoolConverterService.convertToSchoolDtoControlList();
		
		assertTrue(schoolDtoControlList.isEmpty());
		verify(schoolService, times(1)).findAll();
	}
	
	@Test
	public void convertToSchoolDtoControlList_void_ShouldReturnNotEmptySchoolsDTOControlList() {
		when(schoolService.findAll()).thenReturn(schools());
		List<SchoolDtoControl> schoolDtoControlList = schoolConverterService.convertToSchoolDtoControlList();
		
		assertFalse(schoolDtoControlList.isEmpty());
		assertEquals(Integer.valueOf(1), schoolDtoControlList.get(0).getId());
		assertEquals("school of natural sciences", schoolDtoControlList.get(0).getName());
		verify(schoolService, times(1)).findAll();
	}
	
	@Test
	public void convertToSchoolDtoControlList_Locale_ShouldReturnEmptySchoolsDTOControlList() {
		Locale locale = mock(Locale.class);
		when(schoolService.findAll()).thenReturn(new ArrayList<>());
		List<SchoolDtoControl> schoolDtoControlList = schoolConverterService.convertToSchoolDtoControlList(locale);
		
		assertTrue(schoolDtoControlList.isEmpty());
		verify(schoolService, times(1)).findAll();
		verify(schoolTitleService, never()).getSchoolTitle(any(Locale.class), any(School.class));
	}
	
	@Test
	public void convertToSchoolDtoControlList_Locale_ShouldReturnNotEmptySchoolsDTOControlList() {
		Locale locale = mock(Locale.class);
		List<School> schools = schools();
		when(schoolService.findAll()).thenReturn(schools);
		when(schoolTitleService.getSchoolTitle(locale, schools.get(0))).thenReturn("школа естественных наук");
		List<SchoolDtoControl> schoolDtoControlList = schoolConverterService.convertToSchoolDtoControlList(locale);
		
		assertFalse(schoolDtoControlList.isEmpty());
		assertEquals("школа естественных наук", schoolDtoControlList.get(0).getTranslation());
		verify(schoolService, times(1)).findAll();
		verify(schoolTitleService, times(1)).getSchoolTitle(any(Locale.class), any(School.class));
	}
	
	public List<School> schools() {
		List<School> schools = new ArrayList<>();
		School school = new School();
		school.setId(1);
		school.setName("school of natural sciences");
		schools.add(school);
		return schools;
	}
	
	public List<School> schoolsWithMajors() {
		List<School> schools = new ArrayList<>();
		School school = new School();
		school.setId(1);
		school.setName("school of natural sciences");
		
		Major major = new Major();
		major.setName("management");
		school.setMajors(Arrays.asList(major));
		
		schools.add(school);
		return schools;
	}
	
	public List<School> schoolsWithoutMajors() {
		List<School> schools = new ArrayList<>();
		School school = new School();
		school.setId(1);
		school.setName("school of natural sciences");
		school.setMajors(null);
		schools.add(school);
		return schools;
	}
}
