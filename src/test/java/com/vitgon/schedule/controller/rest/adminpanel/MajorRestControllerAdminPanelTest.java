package com.vitgon.schedule.controller.rest.adminpanel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;

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

import com.vitgon.schedule.controller.rest.adminpanel.MajorRestControllerAdminPanel;
import com.vitgon.schedule.dto.AddMajorDto;
import com.vitgon.schedule.dto.EditMajorDto;
import com.vitgon.schedule.model.ApiError;
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.service.MajorDtoService;
import com.vitgon.schedule.service.MessageService;
import com.vitgon.schedule.service.database.MajorService;

@RunWith(MockitoJUnitRunner.class)
public class MajorRestControllerAdminPanelTest {

	@Mock
	private MajorService majorService;
	
	@Mock
	private MessageService messageService;
	
	@Mock
	private MajorDtoService majorDtoConverterService;
	
	@InjectMocks
	private MajorRestControllerAdminPanel majorRestControllerControl;
	
//	@Test
//	public void addMajor_AddMajorDto__success() {
//		AddMajorDto addMajorDto = new AddMajorDto();
//		Major major = new Major();
//		
//		when(majorDtoConverterService.convertToEntity(addMajorDto)).thenReturn(major);
//		ApiSuccess apiSuccess = majorRestControllerControl.addMajor(addMajorDto);
//		
//		assertNotNull(apiSuccess.getTimestamp());
//		assertFalse(apiSuccess.getMessage().isEmpty());
//		verify(majorService, times(1)).save(major);
//	}
	
//	@Test
//	public void updateMajor_EditMajorDto__whenMajorExists_thenSuccess() {
//		EditMajorDto editMajorDto = new EditMajorDto();
//		editMajorDto.setId(45);
//		Major major = new Major();
//		
//		when(majorService.findById(45)).thenReturn(major);
//		when(majorDtoConverterService.convertToEntity(editMajorDto, major)).thenReturn(major);
//		ApiSuccess apiSuccess = majorRestControllerControl.updateMajor(editMajorDto);
//		
//		assertNotNull(apiSuccess.getTimestamp());
//		assertFalse(apiSuccess.getMessage().isEmpty());
//		verify(majorService, times(1)).findById(45);
//		verify(majorDtoConverterService, times(1)).convertToEntity(editMajorDto, major);
//		verify(majorService, times(1)).update(major);
//	}
	
	@Test(expected = IllegalArgumentException.class)
	public void updateMajor_EditMajorDto__whenMajorDoesNotExist_thenThrowException() {
		EditMajorDto editMajorDto = new EditMajorDto();
		editMajorDto.setId(45);
		
		when(majorService.findById(45)).thenReturn(null);
		majorRestControllerControl.updateMajor(editMajorDto);
	}
	
	@Test
	public void deleteMajor_Major_HttpServletRequest__whenMajorExists_thenOKStatus() {
		Major major = new Major();
		HttpServletRequest request = mock(HttpServletRequest.class);
		
		ResponseEntity<?> responseEntity = majorRestControllerControl.deleteMajor(major);
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue(responseEntity.getBody() instanceof ApiSuccess);
		ApiSuccess apiSuccess = (ApiSuccess) responseEntity.getBody();
		assertFalse(apiSuccess.getMessage().isEmpty());
		verify(majorService, times(1)).delete(major);
	}
	
	@Test
	public void deleteMajor_Major_HttpServletRequest__whenMajorDoesNotExist_thenSuccess() {
		Major major = new Major();
		HttpServletRequest request = mock(HttpServletRequest.class);
		
		ResponseEntity<?> responseEntity = majorRestControllerControl.deleteMajor(null);
		
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		assertTrue(responseEntity.getBody() instanceof ApiError);
		ApiError apiError = (ApiError) responseEntity.getBody();
		assertFalse(apiError.getMessage().isEmpty());
		
		Map<String, List<String>> errors = (Map) apiError.getDetails();
		assertTrue(errors.containsKey("majorId"));
		verify(messageService, times(1)).getMessage(anyString());
	}
}
