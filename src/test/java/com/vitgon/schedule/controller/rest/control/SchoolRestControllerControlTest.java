package com.vitgon.schedule.controller.rest.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import com.vitgon.schedule.dto.AddSchoolDto;
import com.vitgon.schedule.dto.EditSchoolDto;
import com.vitgon.schedule.model.ApiError;
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.Locale;
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
	
	@Test
	public void updateSchool_EditSchoolDto_whenProvidedSchoolExists_thenSuccess() {
		EditSchoolDto editSchoolDto = new EditSchoolDto();
		editSchoolDto.setSchoolId(22);
		editSchoolDto.setNewSchoolName("school of humanities");
		School school = spy(new School());
		
		when(schoolService.findById(any(Integer.class))).thenReturn(school);
		ApiSuccess apiSuccess = schoolRestControllerControl.updateSchool(editSchoolDto);
		
		assertNotNull(apiSuccess.getTimestamp());
		assertFalse(apiSuccess.getMessage().isEmpty());
		verify(schoolService, times(1)).findById(22);
		
		assertFalse(school.getUrl().isEmpty());
		assertFalse(school.getName().isEmpty());
		verify(schoolService, times(1)).update(school);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void updateSchool_EditSchoolDto_whenProvidedSchoolDoesNotExist_thenThrowException() {
		EditSchoolDto editSchoolDto = new EditSchoolDto();
		when(schoolService.findById(any(Integer.class))).thenReturn(null);
		schoolRestControllerControl.updateSchool(editSchoolDto);
	}
	
	@Test
	public void deleteSchool_School_HttpServletRequest__whenProvidedSchoolExists_thenOKResponse() {
		School school = new School();
		HttpServletRequest request = mock(HttpServletRequest.class);
		
		ResponseEntity<?> responseEntity = schoolRestControllerControl.deleteSchool(school, request);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue(responseEntity.getBody() instanceof ApiSuccess);
		ApiSuccess apiSuccess = (ApiSuccess) responseEntity.getBody();
		assertNotNull(apiSuccess.getTimestamp());
		assertFalse(apiSuccess.getMessage().isEmpty());
		verify(schoolService, times(1)).delete(school);
	}
	
	@Test
	public void deleteSchool_School_HttpServletRequest__whenProvidedSchoolDoesNotExists_thenBadRequestResponse() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		
		ResponseEntity<?> responseEntity = schoolRestControllerControl.deleteSchool(null, request);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		assertTrue(responseEntity.getBody() instanceof ApiError);
		ApiError apiError = (ApiError) responseEntity.getBody();
		assertNotNull(apiError.getTimestamp());
		assertFalse(apiError.getMessage().isEmpty());
		
		assertNotNull(apiError.getDetails());
		Map<String, List<String>> errors = (Map) apiError.getDetails();
		assertNotNull(errors.containsKey("schoolId"));
		assertNotNull(errors.get("schoolId"));
		assertFalse(errors.get("schoolId").isEmpty());
	}
	
	@Test
	public void getSchoolsView_localeId__whenLocaleIdGreaterThanZeroAndLocaleIsNotNull_thenSuccess() {
		Locale locale = new Locale();
		
		when(localeService.findById(1)).thenReturn(locale);
		when(schoolConverterService.convertToSchoolDtoControlList(locale)).thenReturn(new ArrayList());
		ModelAndView modelAndView = schoolRestControllerControl.getSchoolsView(1);
		
		assertEquals("control/schools-list :: schools-list", modelAndView.getViewName());
		assertNotNull(modelAndView.getModelMap().containsKey("schoolDtoList"));
		verify(localeService, times(1)).findById(1);
		verify(schoolConverterService, times(1)).convertToSchoolDtoControlList(locale);
	}
}