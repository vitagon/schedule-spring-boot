package com.vitgon.schedule.controller.rest.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.vitgon.schedule.dto.AddSchoolDto;
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.service.MessageService;
import com.vitgon.schedule.service.SchoolConverterService;
import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.service.database.SchoolService;

@RunWith(MockitoJUnitRunner.class)
public class SchoolRestControllerControlTest {

	@Mock
	private SchoolService schoolService;
	
	@Mock
	private SchoolConverterService schoolConverterService;

	@Mock
	private LocaleService localeService;

	@Mock
	private MessageService messageService;
	
	@InjectMocks
	private SchoolRestControllerControl schoolRestControllerControl;

	@Test
	public void addSchool_AddSchoolDto_success() {
		AddSchoolDto addSchoolDTO = new AddSchoolDto();
		addSchoolDTO.setSchoolName("school of humanities");
		
		ApiSuccess apiSuccess = schoolRestControllerControl.addSchool(addSchoolDTO);
		assertNotNull(apiSuccess.getTimestamp());
		assertFalse(apiSuccess.getMessage().isEmpty());
		verify(schoolService, times(1)).save(any(School.class));
	}
}
