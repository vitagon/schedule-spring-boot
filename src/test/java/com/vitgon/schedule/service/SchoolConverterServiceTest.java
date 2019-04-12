package com.vitgon.schedule.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import com.vitgon.schedule.dto.SchoolDto;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.service.database.SchoolService;

public class SchoolConverterServiceTest {
	
	private SchoolConverterService schoolMapperService;
	
	private SchoolService schoolService;
	private SchoolTitleService schoolTitleService;
	private MajorTitleService majorTitleService;
	
	@Before
	public void init() {
		schoolService = Mockito.mock(SchoolService.class);
		schoolTitleService = Mockito.mock(SchoolTitleService.class);
		majorTitleService = Mockito.mock(MajorTitleService.class);
		
		schoolMapperService = new SchoolConverterService(schoolService, schoolTitleService, majorTitleService);
	}
	
	@Test
	public void testMapAllToSchoolDTOListMethod() {
		List<School> schools = schools();
		int schoolId = schools.get(0).getId();
		String schoolTitle = schools.get(0).getName();
		
		BDDMockito.when(schoolService.findAll()).thenReturn(schools);
		List<SchoolDto> schoolDtoList = schoolMapperService.convertToSchoolDtoList();
		Assert.assertEquals(schoolId, schoolDtoList.get(0).getId());
		Assert.assertEquals(schoolTitle, schoolDtoList.get(0).getName());
		verify(schoolService, times(1)).findAll();
	}

	@Test
	public void testMapAllToSchoolDTOListMethodWithLocale() {
		Locale locale = Mockito.mock(Locale.class);
		List<School> schools = schools();
		int schoolId = schools.get(0).getId();
		String schoolTitle = schools.get(0).getName();
		
		BDDMockito.when(schoolService.findAll()).thenReturn(schools);
		BDDMockito.when(schoolTitleService.getSchoolTitle(any(Locale.class), any(School.class)))
				.thenReturn(schoolTitle);
		List<SchoolDto> schoolDtoList = schoolMapperService.convertToSchoolDtoList(locale);
		Assert.assertEquals(schoolId, schoolDtoList.get(0).getId());
		Assert.assertEquals(schoolTitle, schoolDtoList.get(0).getName());
		verify(schoolService, times(1)).findAll();
		verify(schoolTitleService, times(1)).getSchoolTitle(ArgumentMatchers.any(Locale.class), ArgumentMatchers.any(School.class));
	}
	
	@Test
	public void testMapAllToMap() {
		Locale locale = Mockito.mock(Locale.class);
		List<School> schools = schools();
		
		int schoolId = schools.get(0).getId();
		String schoolTitle = schools.get(0).getName();
		
		BDDMockito.when(schoolService.findAll()).thenReturn(schools);
		BDDMockito.when(schoolTitleService.getSchoolTitle(any(Locale.class), any(School.class)))
			.thenReturn(schoolTitle);
		Map<Integer, String> schoolsMap = schoolMapperService.convertToMap(locale);
		
		Assert.assertTrue(schoolsMap.containsKey(schoolId));
		Assert.assertEquals(schoolTitle, schoolsMap.get(schoolId));
		verify(schoolService, times(1)).findAll();
		verify(schoolTitleService, times(1)).getSchoolTitle(any(Locale.class), any(School.class));
	}
	
	@Test
	public void testPrepareSchoolPojos() {
		Locale locale = Mockito.mock(Locale.class);
		List<School> schools = schoolsWithMajors();
		int schoolId = schools.get(0).getId();
		String schoolName = schools.get(0).getName();
		String majorName = schools.get(0).getMajors().get(0).getName();
		
		when(schoolTitleService.getSchoolTitle(any(Locale.class), any(School.class)))
			.thenReturn(schoolName);
		when(majorTitleService.getMajorTitle(any(Locale.class), any(Major.class)))
			.thenReturn(majorName);
		List<SchoolDto> schoolDtoList = schoolMapperService.convertToSchoolDtoList(schools, locale);
		Assert.assertEquals(schoolId, schoolDtoList.get(0).getId());
		Assert.assertEquals(schoolName, schoolDtoList.get(0).getName());
		Assert.assertEquals(majorName, schoolDtoList.get(0).getMajors().get(0).getName());
		
		verify(schoolTitleService, times(1)).getSchoolTitle(any(Locale.class), any(School.class));
		verify(majorTitleService, times(1)).getMajorTitle(any(Locale.class), any(Major.class));
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
}
