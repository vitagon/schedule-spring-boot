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
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vitgon.schedule.dto.GroupDto;
import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.projection.GroupProjection;
import com.vitgon.schedule.service.database.GroupService;

@Service
public class GroupDtoService {
	
	private MessageService messageService;
	private GroupService groupService;
	private LocaleConverterService localeConverterService;

	public GroupDtoService(GroupService groupService, MessageService messageService, LocaleConverterService localeConverterService) {
		this.groupService = groupService;
		this.messageService = messageService;
		this.localeConverterService = localeConverterService;
	}
	
	public String getDegree(Group group) {
		return group.getMajor().getDegree().name();
	}
	
	public String getDegreeTranslation(String degree, Locale locale) {
		java.util.Locale localeUtil = new java.util.Locale(locale.getCode());
		return messageService.getMessage(degree, localeUtil);
	}
	
	public List<GroupDto> getGroupDtoList() {
		Locale locale = localeConverterService.getClientLocale();
		List<GroupDto> groupDtoList = new ArrayList<>();
		List<GroupProjection> groups = groupService.getAllByLocaleId(locale.getId());
		for (GroupProjection group : groups) {
			groupDtoList.add(new GroupDto(group.getId(), group.getTranslation()));
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

		for (GroupProjection groupProjection : groups) {
			int courseNum = groupProjection.getCourse_num();

			if (!groupsMap.containsKey(courseNum)) {
				groupsMap.put(courseNum, new ArrayList<>());
			}

			List<GroupDto> groupsList = groupsMap.get(courseNum);
			groupsList.add(new GroupDto(groupProjection.getId(), groupProjection.getTranslation()));
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

	public List<GroupDto> getGroupDtoListByMajorId(Integer majorId) {
		Locale locale = localeConverterService.getClientLocale();
		return getGroupDtoListByMajorIdAndLocaleId(majorId, locale.getId());
	}

	public List<GroupDto> getGroupDtoListByMajorIdAndLocaleId(Integer majorId, Integer localeId) {
		List<GroupProjection> groupProjectionList = groupService.getAllByMajorIdAndLocaleId(majorId, localeId);

		return groupProjectionList.stream()
				.map(projection -> {
					GroupDto groupDto = new GroupDto();
					groupDto.setId(projection.getId());
					groupDto.setName(projection.getName());
					groupDto.setTranslation(projection.getTranslation());
					groupDto.setCourseNum(projection.getCourse_num());
					groupDto.setMajorId(majorId);
					return groupDto;
				}).collect(Collectors.toList());
	}

	public GroupDto getGroupDtoByGroupIdAndLocaleId(Integer groupId, Integer localeId) {
		GroupProjection groupProjection = groupService.getByGroupIdAndLocaleId(groupId, localeId);
		
		GroupDto groupDto = new GroupDto();
		groupDto.setId(groupProjection.getId());
		groupDto.setName(groupProjection.getName());
		groupDto.setTranslation(groupProjection.getTranslation());
		groupDto.setCourseNum(groupProjection.getCourse_num());
		groupDto.setMajorId(groupProjection.getMajor_id());
		return groupDto;
	}

	public GroupDto getGroupDtoByGroupId(Integer groupId) {
		Locale locale = localeConverterService.getClientLocale();
		return getGroupDtoByGroupIdAndLocaleId(groupId, locale.getId());
	}
}
