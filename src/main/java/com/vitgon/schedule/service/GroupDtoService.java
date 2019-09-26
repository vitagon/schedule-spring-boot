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
import com.vitgon.schedule.service.database.LocaleService;

@Service
public class GroupDtoService {
	
	private MessageService messageService;
	private GroupService groupService;
	private LocaleConverterService localeConverterService;
	private LocaleService localeService;

	public GroupDtoService(GroupService groupService, MessageService messageService, LocaleConverterService localeConverterService, LocaleService localeService) {
		this.groupService = groupService;
		this.messageService = messageService;
		this.localeConverterService = localeConverterService;
		this.localeService = localeService;
	}

	public String getGroupNameTranslation(GroupProjection group, Locale locale) {
		java.util.Locale localeUtil = new java.util.Locale(locale.getCode());  
		String degreeTranslation = messageService.getMessage(group.getDegree().name().toLowerCase(), localeUtil);
		return Character.toUpperCase(degreeTranslation.charAt(0)) +
				String.valueOf(group.getNumber()) +
				group.getSuffix_translation();
	}
	
	public String getGroupName(GroupProjection group) {
		String degree = group.getDegree().name().toLowerCase();
		return Character.toUpperCase(degree.charAt(0)) +
				String.valueOf(group.getNumber()) +
				group.getSuffix();
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
			groupDtoList.add(new GroupDto(group.getId(), getGroupNameTranslation(group, locale)));
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
		Locale locale = localeService.findById(localeId).get();

		for (GroupProjection group : groups) {
			int courseNum = group.getCourse_num();

			if (!groupsMap.containsKey(courseNum)) {
				groupsMap.put(courseNum, new ArrayList<>());
			}

			List<GroupDto> groupsList = groupsMap.get(courseNum);
			groupsList.add(new GroupDto(group.getId(), getGroupNameTranslation(group, locale)));
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
		Locale locale = localeService.findById(localeId).get();

		return groupProjectionList.stream()
				.map(projection -> {
					GroupDto groupDto = new GroupDto();
					groupDto.setId(projection.getId());
					groupDto.setName(getGroupName(projection));
					groupDto.setNameTranslation(getGroupNameTranslation(projection, locale));
					groupDto.setCourseNum(projection.getCourse_num());
					groupDto.setNumber(projection.getNumber());
					groupDto.setSuffix(projection.getSuffix());
					groupDto.setSuffixTranslation(projection.getSuffix_translation());
					groupDto.setDegree(projection.getDegree().name().toLowerCase());
					groupDto.setDegreeTranslation(getDegreeTranslation(projection.getDegree().name().toLowerCase(), locale));
					groupDto.setMajorId(majorId);
					return groupDto;
				}).collect(Collectors.toList());
	}

	public GroupDto getGroupDtoByGroupIdAndLocaleId(Integer groupId, Integer localeId) {
		GroupProjection groupProjection = groupService.getByGroupIdAndLocaleId(groupId, localeId);
		Locale locale = localeService.findById(localeId).get();
		
		GroupDto groupDto = new GroupDto();
		groupDto.setId(groupProjection.getId());
		groupDto.setName(getGroupName(groupProjection));
		groupDto.setNameTranslation(getGroupNameTranslation(groupProjection, locale));
		groupDto.setCourseNum(groupProjection.getCourse_num());
		groupDto.setNumber(groupProjection.getNumber());
		groupDto.setSuffix(groupProjection.getSuffix());
		groupDto.setSuffixTranslation(groupProjection.getSuffix_translation());
		groupDto.setDegree(groupProjection.getDegree().name().toLowerCase());
		groupDto.setDegreeTranslation(getDegreeTranslation(groupProjection.getDegree().name().toLowerCase(), locale));
		groupDto.setMajorId(groupProjection.getMajor_id());
		return groupDto;
	}
}
