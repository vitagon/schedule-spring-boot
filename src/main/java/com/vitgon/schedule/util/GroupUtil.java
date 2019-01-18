package com.vitgon.schedule.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.vitgon.schedule.model.Group;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.pojo.group.CourseNum;
import com.vitgon.schedule.pojo.group.GroupPojo;
import com.vitgon.schedule.resolver.UrlLocaleResolver;

public class GroupUtil {
	public static String getGroupTitle(Group group, Locale locale) {
		if (locale.getCode().equals(UrlLocaleResolver.EN)) {
			return group.getName();
		}
		
		return group.getGroupTranslations().stream()
				.filter(x -> locale == x.getLocale())
				.map(x -> x.getTitle())
				.findFirst().get();
	}
	
	public static Map<CourseNum, List<GroupPojo>> prepareGroupPojos(List<Group> groups, Locale locale) {
		Map<CourseNum, List<GroupPojo>> groupsMap = new HashMap<>();
		
		for (Group group : groups) {
			CourseNum courseNum = new CourseNum(group.getCourseNum());
			
			if (!groupsMap.containsKey(courseNum)) {
				groupsMap.put(courseNum, new ArrayList<>());
			}
			
			List<GroupPojo> groupsList = groupsMap.get(courseNum);
			groupsList.add(new GroupPojo(
					group.getId(),
					getGroupTitle(group, locale)
			));
		}
		
		return sortGroupsMap(groupsMap);
	}
	
	public static Map<CourseNum, List<GroupPojo>> sortGroupsMap(Map<CourseNum, List<GroupPojo>> unsortedGroupsMap) {
		List<Entry<CourseNum, List<GroupPojo>>> list = new LinkedList<Map.Entry<CourseNum, List<GroupPojo>>>(unsortedGroupsMap.entrySet());
		
		Collections.sort(list, new Comparator<Entry<CourseNum, List<GroupPojo>>>() {
			public int compare(Map.Entry<CourseNum, List<GroupPojo>> o1, Map.Entry<CourseNum, List<GroupPojo>> o2) {
				return (o1.getKey()).compareTo(o2.getKey());
			}
		});

		//convert sortedMap back to Map
		Map<CourseNum, List<GroupPojo>> sortedMap = new LinkedHashMap<CourseNum, List<GroupPojo>>();
		for (Entry<CourseNum, List<GroupPojo>> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
	
	public static String transliterateTo(String groupTitle) {
		return null;
	}
}
