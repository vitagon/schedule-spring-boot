package com.vitgon.schedule.controller.rest.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import com.vitgon.schedule.controller.rest.SubjectRestController;
import com.vitgon.schedule.dto.AddSubjectDto;
import com.vitgon.schedule.dto.EditSubjectDto;
import com.vitgon.schedule.model.ApiError;
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.service.MessageService;
import com.vitgon.schedule.service.database.SubjectService;

@RunWith(MockitoJUnitRunner.class)
public class SubjectRestControllerControlTest {
	
	@Mock
	private SubjectRestController subjectRestController;

	@Mock
	private SubjectService subjectService;

	@Mock
	private MessageService messageService;
	
	@InjectMocks
	private SubjectRestControllerControl subjectRestControllerControl;

//	@Test
//	public void addSubject_AddSubjectDto__success() {
//		AddSubjectDto addSubjectDto = new AddSubjectDto();
//		addSubjectDto.setSubjectName("math");
//		
//		ApiSuccess apiSuccess = subjectRestControllerControl.addSubject(addSubjectDto);
//		assertNotNull(apiSuccess.getTimestamp());
//		assertFalse(apiSuccess.getMessage().isEmpty());
//		verify(subjectService, times(1)).save(any(Subject.class));
//	}
	
//	@Test
//	public void updateSubject_EditSubjectDto__whenSubjectIsNotNull_thenSuccess() {
//		EditSubjectDto editSubjectDto = new EditSubjectDto();
//		editSubjectDto.setOldSubjectId(32);
//		editSubjectDto.setNewSubjectName("math");
//		Subject subjectForUpdate = new Subject();
//		
//		when(subjectService.findById(32)).thenReturn(subjectForUpdate);
//		ApiSuccess apiSuccess = subjectRestControllerControl.updateSubject(editSubjectDto);
//		
//		assertNotNull(apiSuccess.getTimestamp());
//		assertFalse(apiSuccess.getMessage().isEmpty());
//		assertEquals("math", subjectForUpdate.getName());
//		verify(subjectService, times(1)).findById(32);
//		verify(subjectService, times(1)).update(subjectForUpdate);
//	}
	
//	@Test(expected = IllegalArgumentException.class)
//	public void updateSubject_EditSubjectDto__whenSubjectIsNull_thenThrowException() {
//		EditSubjectDto editSubjectDto = new EditSubjectDto();
//		editSubjectDto.setOldSubjectId(32);
//		editSubjectDto.setNewSubjectName("math");
//		
//		when(subjectService.findById(32)).thenReturn(null);
//		subjectRestControllerControl.updateSubject(editSubjectDto);
//	}
	
	@Test
	public void deleteSubject_Subject_HttpServletRequest__whenSubjectExists_thenOKStatus() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		Subject subjectForDeletion = new Subject(); 
		ResponseEntity<?> responseEntity = subjectRestControllerControl.deleteSubject(subjectForDeletion, request);
		
		assertTrue(responseEntity.getBody() instanceof ApiSuccess);
		ApiSuccess apiSuccess = (ApiSuccess) responseEntity.getBody();
		assertNotNull(apiSuccess.getTimestamp());
		assertFalse(apiSuccess.getMessage().isEmpty());
		verify(subjectService, times(1)).delete(subjectForDeletion);
	}
	
	@Test
	public void deleteSubject_Subject_HttpServletRequest__whenSubjectDoesNotExist_thenBadRequestStatus() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		ResponseEntity<?> responseEntity = subjectRestControllerControl.deleteSubject(null, request);
		
		assertTrue(responseEntity.getBody() instanceof ApiError);
		ApiError apiError = (ApiError) responseEntity.getBody();
		assertNotNull(apiError.getTimestamp());
		assertFalse(apiError.getMessage().isEmpty());
		Map<String, List<String>> errors = (Map) apiError.getDetails();
		assertTrue(errors.containsKey("subjectId"));
		verify(subjectService, never()).delete(any(Subject.class));
	}
	
//	@Test
//	public void getSubjectsWithView_LocaleId__success() {
//		when(subjectRestController.getSubjectDtoListByLocale(1)).thenReturn(new ArrayList<>());
//		ModelAndView modelAndView = subjectRestControllerControl.getSubjectsWithView(1);
//		
//		assertFalse(modelAndView.getViewName().isEmpty());
//		assertNotNull(modelAndView.getModelMap().get("subjects"));
//		verify(subjectRestController, times(1)).getSubjectDtoListByLocale(1);
//	}
}
