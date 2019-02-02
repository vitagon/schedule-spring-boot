package com.vitgon.schedule.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import com.vitgon.schedule.collection.ScheduleTree;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Schedule;
import com.vitgon.schedule.pojo.SchedulePOJO;
import com.vitgon.schedule.pojo.TeacherPOJO;

public class ScheduleUtil {
	
	public static final String WEEK_UP = "up";
	public static final String WEEK_DOWN = "down";
	
	private final static Map<Integer, String> DAYS_MAP = new HashMap<>();
	
	static {
		Days[] days = Days.values();
		for (Days day : days) {
			int dayNum = day.getDayNum();
			String title = day.name();
			DAYS_MAP.put(dayNum, title.toLowerCase());
		}
	}
	
	public static void main(String... args) {
		System.out.println(DAYS_MAP);
	}
	
	private enum Days {
		SUNDAY(7), MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6);
		
		private int dayNum;
		
		Days(int dayNum) {
			this.dayNum = dayNum;
		}
		
		public int getDayNum() {
			return this.dayNum;
		}
	}
	
	public static ScheduleTree getScheduleTree(List<Schedule> schedules, Locale locale) {
		ScheduleTree scheduleTree = new ScheduleTree();
		for (Schedule schedule : schedules) {
			String dayTitle = DAYS_MAP.get(schedule.getDayNum());
			int lessonNum = schedule.getLessonNum();
			String weekType = schedule.getWeek();
			
			SchedulePOJO schedulePOJO = new SchedulePOJO();
			schedulePOJO.setScheduleId(schedule.getId());
			schedulePOJO.setSubjId(schedule.getSubject().getId());
			schedulePOJO.setSubjectTitle(SubjectUtil.getSubjectTitle(schedule.getSubject(), locale));
			schedulePOJO.setLessonType(LessonUtil.getLessonType(schedule.getLessonType()));
			
			if (schedule.getUser() != null) {
				schedulePOJO.setTeacher(new TeacherPOJO(
						schedule.getUser().getId(),
						UserUtil.makeupUsername(schedule.getUser(), locale)
				));
			}
			
			schedulePOJO.setClassroom(schedule.getClassroom());
			scheduleTree.add(dayTitle, lessonNum, weekType, schedulePOJO);
		}
		return scheduleTree;
	}
	
	/**
	 * Schedule map, example for Monday:
	 * [
	 *     'monday' => [
	 *     		// subjectsPairMap(int => subjectsPair) 
	 *     		1 => [
	 *     			// subjectsPair
	 *     			'up' => [
	 *     				'scheduleId'   => String,
	 *		   			'subjId'       => String,
	 *		   			'subjectTitle' => String,
	 *		  			'lessonType'   => String,
	 *		  			'classroom'	   => String,
	 *		  			'teacher' => [
	 *		  				'id'   => String,
	 *		  				'name' => String
     *			  		]
     *			  	],
	 *		  		'down' => [
	 *					'scheduleId'   => String,
	 *		  			'subjId'       => String,
	 *		  			'subjectTitle' => String,
	 *		  			'lessonType'   => String,
	 *		  			'classroom'	   => String,
	 *		  			'teacher' => [
	 *		  				'id'   => String,
     *			  				'name' => String
	 *		  			]
     *			  	]
	 *     		],
	 *     		2 => [
	 *     			...
	 *     		],
	 *     		3 => [
	 *     			...
	 *     		],
	 *    		4 => [
	 *              ...
	 *     		],
	 *     		5 => [
	 *     			...
	 *     		],
	 *     		6 => [
	 *     			...
	 *     		],
	 *     		7 => [
	 *     			...
	 *     		]
	 *     ],
	 *     'tuesday' => [
	 *     		...
	 *     ]
	 * ]
	 * 
	 * 
	 * @param schedules
	 * @param locale
	 * @return
	 */
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public static Map<String, Map<Integer, Map>> getScheduleMap(List<Schedule> schedules, Locale locale) {
//		Map<String, Map<Integer, Map>> rootMap = new HashMap<>();
//		Map<Integer, String> daysMap = DAYS_MAP;
//	
//		for (Schedule schedule : schedules) {
//			String dayTitle = daysMap.get(schedule.getDayNum());
//			int subjectNum = schedule.getLessonNum();
//			
//			if (rootMap.containsKey(dayTitle)) {
//				Map<Integer, Map> subjectsPairMap = rootMap.get(dayTitle);
//				
//				Optional<Map> subjectsPairOptional = Optional.of(subjectsPairMap)
//					.map(x -> x.get(subjectNum));
//				
//				if (subjectsPairOptional.isPresent()) {
//					Map subjectsPair = subjectsPairOptional.get();
//					setSubjectsPair(subjectsPair, schedule, locale);
//				} else {
//					Map subjectsPair = new HashMap<>();
//					subjectsPairMap.put(subjectNum, subjectsPair);
//					setSubjectsPair(subjectsPair, schedule, locale);
//				}
//			} else {
//				Map<Integer, Map> subjectsPairMap = new HashMap<>();
//				
//				Map subjectsPair = new HashMap<>();
//				subjectsPairMap.put(subjectNum, subjectsPair);
//				setSubjectsPair(subjectsPair, schedule, locale);
//				
//				rootMap.put(dayTitle, subjectsPairMap);
//			}
//		}
//		
//		return sortByDay(rootMap);
//	}
	
	
	
	/**
	 * SubjectsPair map:
	 * 	[
	 * 		'up' => [
	 * 			'subjId'       => String,
	 * 			'subjectTitle' => String,
	 * 			'lessonType'   => String,
	 * 			'classroom'	   => String,
	 * 			'teacher' => [
	 * 				'id'   => String,
	 * 				'name' => String
	 * 			]
	 * 		],
	 * 		'down' => [
	 * 			'scheduleId'   => String,
	 * 			'subjId'       => String,
	 * 			'subjectTitle' => String,
	 * 			'lessonType'   => String,
	 * 			'classroom'	   => String,
	 * 			'teacher' => [
	 * 				'id'   => String,
	 * 				'name' => String
	 * 			]
	 * 		]
	 * ]
	 * 
	 * @param subjectsPair
	 * @param schedule
	 * @param locale
	 */
//	public static void setSubjectsPair(Map<String, Map> subjectsPair, Schedule schedule, Locale locale) {
//		int subjId = schedule.getSubject().getId();
//		int week = schedule.getWeek();
//		String subjectTitle = SubjectUtil.getSubjectTitle(schedule.getSubject(), locale);
//		String teacherName = UserUtil.makeupUsername(schedule.getUser(), locale);
//		String lessonType = LessonUtil.getLessonType(schedule.getLessonType());
//		String classroom = schedule.getClassroom();
//		
//		Map<String, Object> subjectMap = new HashMap<>();
//		subjectMap.put("scheduleId", String.valueOf(schedule.getId()));
//		subjectMap.put("subjId", String.valueOf(subjId));
//		subjectMap.put("subjectTitle", subjectTitle);
//		subjectMap.put("lessonType", lessonType);
//		subjectMap.put("classroom", classroom);
//		
//		if (teacherName != null) {
//			Map<String, String> teacherMap = new HashMap<>();
//			teacherMap.put("id", schedule.getUser().getId().toString());
//			teacherMap.put("name", teacherName);
//			
//			subjectMap.put("teacher", teacherMap);
//		}
//		
//		
//		if (week == WEEK_UP) {
//			subjectsPair.put("up", subjectMap);
//		}
//		
//		if (week == WEEK_DOWN){
//			subjectsPair.put("down", subjectMap);
//		}
//	}
	
	/**
	 * 
	 * @param scheduleMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Map<Integer, Map>> sortByDay(Map<String, Map<Integer, Map>> scheduleMap) {
		List<Entry<String, Map<Integer, Map>>> scheduleEntries =
				new ArrayList<Entry<String, Map<Integer, Map>>>(scheduleMap.entrySet());
		
		Collections.sort(scheduleEntries, new Comparator<Entry<String, Map<Integer, Map>>>() {

			@Override
			public int compare(
					Entry<String, Map<Integer, Map>> o1,
					Entry<String, Map<Integer, Map>> o2) {
				int dayNum1 = getOrderNum(o1.getKey());
				int dayNum2 = getOrderNum(o2.getKey());
				
				if (dayNum1 > dayNum2) {
					return 1;
				}
				
				if (dayNum1 < dayNum2) {
					return -1;
				}
				
				return 0;
			}
			
		});
		
		Map<String, Map<Integer, Map>> sortedMap = new LinkedHashMap<String, Map<Integer, Map>>();
        for (Map.Entry<String, Map<Integer, Map>> entry : scheduleEntries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
		
		return sortedMap;
	}
	
	/**
	 * 
	 * @param dayTitle
	 * @return
	 */
	public static int getOrderNum(String dayTitle) {
		Map<Integer, String> daysMap = DAYS_MAP;
		return daysMap.entrySet().stream()
				.filter(entry -> Objects.equals(entry.getValue(), dayTitle))
				.map(Map.Entry::getKey)
				.findFirst().get();
	}
}
