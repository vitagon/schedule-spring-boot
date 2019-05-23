package com.vitgon.schedule.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vitgon.schedule.dto.AddMajorDto;
import com.vitgon.schedule.dto.DegreeEnum;
import com.vitgon.schedule.dto.EditMajorDto;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.service.database.SchoolService;

public class MajorDtoConverterServiceTest {
	
	private SchoolService schoolService;
	private MajorDtoConverterService majorDtoConverterService;
	
	@Before
	public void init() {
		schoolService = mock(SchoolService.class);
		majorDtoConverterService = new MajorDtoConverterService(schoolService);
	}
	
//	@Test
//	public void testConvertToEntityWithAddMajorDto() {
//		AddMajorDto addMajorDto = createAddMajorDto();
//		School school = createSchool();
//		when(schoolService.findById(addMajorDto.getSchoolId())).thenReturn(school);
//		
//		Major major = majorDtoConverterService.convertToEntity(addMajorDto);
//		Assert.assertEquals(Integer.valueOf(5), major.getSchool().getId());
//		Assert.assertEquals("applied computer science", major.getName());
//		Assert.assertEquals("applied_computer_science", major.getUrl());
//		Assert.assertEquals(4, major.getDuration());
//		Assert.assertEquals(DegreeEnum.BACHELORS, major.getDegree());
//		
//		verify(schoolService).findById(addMajorDto.getSchoolId());
//	}
	
	@Test
	public void testConvertToEntityWithEditMajorDtoAndMajor() {
		EditMajorDto editMajorDto = createEditMajorDto();
		
		Major major = majorDtoConverterService.convertToEntity(editMajorDto, new Major());
		Assert.assertEquals("applied computer science", major.getName());
		Assert.assertEquals("applied_computer_science", major.getUrl());
		Assert.assertEquals(4, major.getDuration());
		Assert.assertEquals(DegreeEnum.BACHELORS, major.getDegree());
	}
	
//	private AddMajorDto createAddMajorDto() {
//		AddMajorDto addMajorDto = new AddMajorDto();
//		addMajorDto.setSchoolId(5);
//		addMajorDto.setName("applied computer science");
//		addMajorDto.setDuration(4);
//		addMajorDto.setDegree("BACHELORS");
//		return addMajorDto;
//	}
	
	private EditMajorDto createEditMajorDto() {
		EditMajorDto editMajorDto = new EditMajorDto();
		editMajorDto.setName("applied computer science");
		editMajorDto.setDuration(4);
		editMajorDto.setDegree("BACHELORS");
		return editMajorDto;
	}
	
	private School createSchool() {
		School school = new School();
		school.setId(5);
		return school;
	}
}
