package com.vitgon.schedule.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.vitgon.schedule.dto.GroupDto;
import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.service.database.GroupService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Service
public class GroupMapperService {
	
	private GroupService groupService;

	public String getGroupTitle(Group group) {
		return getDegree(group).charAt(0) + String.valueOf(group.getNumber()) + group.getSuffix();
	}
	
	public String getDegree(Group group) {
		return group.getMajor().getDegree().name();
	}
	
	public List<GroupDto> convertToGroupDtoList() {
		List<GroupDto> groupDtoList = new ArrayList<>();
		List<Group> groups = groupService.findAll();
		for (Group group : groups) {
			groupDtoList.add(new GroupDto(group.getId(), getGroupTitle(group)));
		}
		return groupDtoList;
	}

	public Map<Integer, List<GroupDto>> convertToGroupDtoMap(List<Group> groups) {
		Map<Integer, List<GroupDto>> groupsMap = new HashMap<>();

		for (Group group : groups) {
			int courseNum = group.getCourseNum();

			if (!groupsMap.containsKey(courseNum)) {
				groupsMap.put(courseNum, new ArrayList<>());
			}

			List<GroupDto> groupsList = groupsMap.get(courseNum);
			groupsList.add(new GroupDto(group.getId(), getGroupTitle(group)));
		}

		return sortGroupsMap(groupsMap);
	}

	public Map<Integer, List<GroupDto>> sortGroupsMap(Map<Integer, List<GroupDto>> unsortedGroupsMap) {
		List<Entry<Integer, List<GroupDto>>> list = new LinkedList<Map.Entry<Integer, List<GroupDto>>>(
				unsortedGroupsMap.entrySet());

		Collections.sort(list, new Comparator<Entry<Integer, List<GroupDto>>>() {
			public int compare(Map.Entry<Integer, List<GroupDto>> o1,
					Map.Entry<Integer, List<GroupDto>> o2) {
				return (o1.getKey()).compareTo(o2.getKey());
			}
		});

		// convert sortedMap back to Map
		Map<Integer, List<GroupDto>> sortedMap = new LinkedHashMap<Integer, List<GroupDto>>();
		for (Entry<Integer, List<GroupDto>> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
}
