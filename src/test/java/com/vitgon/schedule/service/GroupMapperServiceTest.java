package com.vitgon.schedule.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.vitgon.schedule.dto.DegreeEnum;
import com.vitgon.schedule.dto.GroupDto;
import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.service.database.GroupService;

public class GroupMapperServiceTest {
	
	private GroupService groupService;
	private GroupMapperService groupMapperService;
	
	private List<Group> groups = new ArrayList<>();
	
	@Before
	public void init() {
		groupService = Mockito.mock(GroupService.class);
		groupMapperService = spy(new GroupMapperService(groupService));
		
		Group firstGroup = new Group(1, null);
		firstGroup.setId(1);
		Group secondGroup = new Group(2, null);
		secondGroup.setId(2);
		groups.add(firstGroup);
		groups.add(secondGroup);
	}
	
	@Test
	public void testGetGroupTitle() {
		Group group = Group.builder()
				.number(1101)
				.suffix("rb")
				.build();
		doReturn(new String("BACHELORS")).when(groupMapperService).getDegree(ArgumentMatchers.any(Group.class));
		String resultTitle = groupMapperService.getGroupTitle(group);
		assertEquals("B1101rb", resultTitle);
		verify(groupMapperService, times(1)).getDegree(ArgumentMatchers.any(Group.class));
	}
	
	@Test
	public void testGetDegree() {
		Major major = new Major();
		major.setDegree(DegreeEnum.BACHELORS);
		Group group = Group.builder()
				.number(1101)
				.suffix("rb")
				.major(major)
				.build();
		String result = groupMapperService.getDegree(group);
		assertEquals("BACHELORS", result);
	}
	 
	@Test
	public void testConvertToGroupDtoList() {
		when(groupService.findAll()).thenReturn(groups);
		doReturn(new String()).when(groupMapperService).getGroupTitle(ArgumentMatchers.any(Group.class));
		List<GroupDto> groupDtoList = groupMapperService.convertToGroupDtoList();
		assertFalse(groupDtoList.isEmpty());
		verify(groupMapperService, times(2)).getGroupTitle(ArgumentMatchers.any());
	}
	
	@Test
	public void testConvertToGroupDtoMap() {
		Mockito.doReturn(new String()).when(groupMapperService).getGroupTitle(ArgumentMatchers.any(Group.class));
		Map<Integer, List<GroupDto>> result = groupMapperService.convertToGroupDtoMap(groups);
		assertNotNull(result.get(1));
		assertNotNull(result.get(2));
		verify(groupMapperService, times(1)).sortGroupsMap(ArgumentMatchers.any());
	}
}
