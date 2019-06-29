package com.vitgon.schedule.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScheduleDto implements Serializable {
	private static final long serialVersionUID = -3539497859405915502L;
	
	private Integer groupId;
	private WeekSchedule up;
	private WeekSchedule down;
	
	@Data
	public static class WeekSchedule {
		private List<LessonSchedule> monday;
		private List<LessonSchedule> tuesday;
		private List<LessonSchedule> wednesday;
		private List<LessonSchedule> thursday;
		private List<LessonSchedule> friday;
		private List<LessonSchedule> saturday;
		private List<LessonSchedule> sunday;
		
		@Data
		public static class LessonSchedule implements Comparable<LessonSchedule> {
			private Integer id;
			private Integer lessonNum;
			private String time;
			private Integer subjectId;
			private String subjectName;
			private Integer lessonTypeId;
			private String lessonTypeName;
			private Integer teacherId;
			private String teacherName;
			private String classroom;
			
			@Override
			public int compareTo(LessonSchedule o) {
				if (o == null || o.getLessonNum() == null) {
					return 0;
				}
				return this.getLessonNum().compareTo(o.getLessonNum());
			}
		}
	}
}
