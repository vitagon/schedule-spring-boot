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
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.vitgon.schedule.dto.AddGroupDto;
import com.vitgon.schedule.dto.EditGroupDto;
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.service.database.GroupService;
import com.vitgon.schedule.service.database.MajorService;

@RunWith(MockitoJUnitRunner.class)
public class GroupRestControllerControlTest {
	
	@Mock
	private GroupService groupService;
	
	@Mock
	private MajorService majorService;
	
	@InjectMocks
	private GroupRestControllerControl groupRestControllerControl;
	
//	@Test
//	public void addGroup_AddGroupDto__whenMajorExists_thenSuccess() {
//		AddGroupDto addGroupDto = new AddGroupDto();
//		addGroupDto.setMajorId(54);
//		Major major = new Major();
//		
//		when(majorService.findById(54)).thenReturn(major);
//		ApiSuccess apiSuccess = groupRestControllerControl.addGroup(addGroupDto);
//		
//		assertNotNull(apiSuccess);
//		assertFalse(apiSuccess.getMessage().isEmpty());
//		verify(majorService, times(1)).findById(54);
//		verify(groupService, times(1)).save(Mockito.any(Group.class));
//	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addGroup_AddGroupDto__whenMajorDoesNotExist_thenThrowException() {
		AddGroupDto addGroupDto = new AddGroupDto();
		addGroupDto.setMajorId(54);
		
		when(majorService.findById(54)).thenReturn(null);
		groupRestControllerControl.addGroup(addGroupDto);
	}
	
//	@Test
//	public void editGroup_EditGroupDto__whenGroupExists_thenSuccess() {
//		EditGroupDto editGroupDto = new EditGroupDto();
//		editGroupDto.setId(54);
//		Group group = new Group();
//		
//		when(groupService.findById(54)).thenReturn(group);
//		ApiSuccess apiSuccess = groupRestControllerControl.editGroup(editGroupDto);
//		
//		assertNotNull(apiSuccess);
//		assertFalse(apiSuccess.getMessage().isEmpty());
//		verify(groupService, times(1)).findById(54);
//		verify(groupService, times(1)).update(group);
//	}
	
	@Test(expected = IllegalArgumentException.class)
	public void editGroup_EditGroupDto__whenGroupDoesNotExist_thenThrowException() {
		EditGroupDto editGroupDto = new EditGroupDto();
		editGroupDto.setId(54);
		
		when(groupService.findById(54)).thenReturn(null);
		groupRestControllerControl.editGroup(editGroupDto);
	}
	
//	@Test
//	public void deleteGroup_Id__whenGroupExists_thenSuccess() {
//		Group group = new Group();
//		
//		when(groupService.findById(54)).thenReturn(group);
//		ApiSuccess apiSuccess = groupRestControllerControl.deleteGroup(54);
//		
//		assertNotNull(apiSuccess);
//		assertFalse(apiSuccess.getMessage().isEmpty());
//		verify(groupService, times(1)).findById(54);
//		verify(groupService, times(1)).deleteById(54);
//	}
	
//	@Test(expected = IllegalArgumentException.class)
//	public void deleteGroup_Id__whenGroupDoesNotExist_thenThrowException() {
//		when(groupService.findById(54)).thenReturn(null);
//		groupRestControllerControl.deleteGroup(54);
//	}
}