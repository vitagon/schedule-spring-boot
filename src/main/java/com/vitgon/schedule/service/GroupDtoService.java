package com.vitgon.schedule.service;

import com.vitgon.schedule.converter.DegreeEnumToStringConverter;
import com.vitgon.schedule.dto.GroupDto;
import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.projection.GroupProjection;
import com.vitgon.schedule.service.database.GroupService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Map.Entry;

@Service
public class GroupDtoService {
	
	private GroupService groupService;
	private DegreeEnumToStringConverter degreeEnumToStringConverter;
	private LocaleConverterService localeConverterService;

	public GroupDtoService(GroupService groupService, DegreeEnumToStringConverter degreeEnumToStringConverter, LocaleConverterService localeConverterService) {
		this.groupService = groupService;
		this.degreeEnumToStringConverter = degreeEnumToStringConverter;
		this.localeConverterService = localeConverterService;
	}

	public String getGroupTitle(GroupProjection group) {
		return degreeEnumToStringConverter.convert(group.getDegree()).charAt(0) +
				String.valueOf(group.getNumber()) +
				group.getSuffix_translation();
	}
	
	public String getDegree(Group group) {
		return group.getMajor().getDegree().name();
	}
	
	public List<GroupDto> getGroupDtoList() {
		Locale locale = localeConverterService.getClientLocale();
		List<GroupDto> groupDtoList = new ArrayList<>();
		List<GroupProjection> groups = groupService.getAllByLocaleId(locale.getId());
		for (GroupProjection group : groups) {
			groupDtoList.add(new GroupDto(group.getId(), getGroupTitle(group)));
		}
		return groupDtoList;
	}
	
	public Map<Integer, List<GroupDto>> getGroupDtoMap(String url) {
		Locale locale = localeConverterService.getClientLocale();
		return getGroupDtoMap(url, locale.getId());
	}

	public Map<Integer, List<GroupDto>> getGroupDtoMap(String url, Integer localeId) {
		Map<Integer, List<GroupDto>> groupsMap = new HashMap<>();
		List<GroupProjection> groups = groupService.getAllByMajorUrlAndLocaleId(url, localeId);

		for (GroupProjection group : groups) {
			int courseNum = group.getCourse_num();

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
