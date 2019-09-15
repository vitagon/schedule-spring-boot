package com.vitgon.schedule.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;


public class ScheduleDto implements Serializable {
	private static final long serialVersionUID = -3539497859405915502L;
	
	private Integer groupId;
	private WeekSchedule up;
	private WeekSchedule down;

	public static class WeekSchedule {
		private List<LessonSchedule> monday;
		private List<LessonSchedule> tuesday;
		private List<LessonSchedule> wednesday;
		private List<LessonSchedule> thursday;
		private List<LessonSchedule> friday;
		private List<LessonSchedule> saturday;
		private List<LessonSchedule> sunday;
		
		
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

			public Integer getId() {
				return id;
			}

			public void setId(Integer id) {
				this.id = id;
			}

			public Integer getLessonNum() {
				return lessonNum;
			}

			public void setLessonNum(Integer lessonNum) {
				this.lessonNum = lessonNum;
			}

			public String getTime() {
				return time;
			}

			public void setTime(String time) {
				this.time = time;
			}

			public Integer getSubjectId() {
				return subjectId;
			}

			public void setSubjectId(Integer subjectId) {
				this.subjectId = subjectId;
			}

			public String getSubjectName() {
				return subjectName;
			}

			public void setSubjectName(String subjectName) {
				this.subjectName = subjectName;
			}

			public Integer getLessonTypeId() {
				return lessonTypeId;
			}

			public void setLessonTypeId(Integer lessonTypeId) {
				this.lessonTypeId = lessonTypeId;
			}

			public String getLessonTypeName() {
				return lessonTypeName;
			}

			public void setLessonTypeName(String lessonTypeName) {
				this.lessonTypeName = lessonTypeName;
			}

			public Integer getTeacherId() {
				return teacherId;
			}

			public void setTeacherId(Integer teacherId) {
				this.teacherId = teacherId;
			}

			public String getTeacherName() {
				return teacherName;
			}

			public void setTeacherName(String teacherName) {
				this.teacherName = teacherName;
			}

			public String getClassroom() {
				return classroom;
			}

			public void setClassroom(String classroom) {
				this.classroom = classroom;
			}
		}

		public List<LessonSchedule> getMonday() {
			return monday;
		}

		public void setMonday(List<LessonSchedule> monday) {
			this.monday = monday;
		}

		public List<LessonSchedule> getTuesday() {
			return tuesday;
		}

		public void setTuesday(List<LessonSchedule> tuesday) {
			this.tuesday = tuesday;
		}

		public List<LessonSchedule> getWednesday() {
			return wednesday;
		}

		public void setWednesday(List<LessonSchedule> wednesday) {
			this.wednesday = wednesday;
		}

		public List<LessonSchedule> getThursday() {
			return thursday;
		}

		public void setThursday(List<LessonSchedule> thursday) {
			this.thursday = thursday;
		}

		public List<LessonSchedule> getFriday() {
			return friday;
		}

		public void setFriday(List<LessonSchedule> friday) {
			this.friday = friday;
		}

		public List<LessonSchedule> getSaturday() {
			return saturday;
		}

		public void setSaturday(List<LessonSchedule> saturday) {
			this.saturday = saturday;
		}

		public List<LessonSchedule> getSunday() {
			return sunday;
		}

		public void setSunday(List<LessonSchedule> sunday) {
			this.sunday = sunday;
		}
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public WeekSchedule getUp() {
		return up;
	}

	public void setUp(WeekSchedule up) {
		this.up = up;
	}

	public WeekSchedule getDown() {
		return down;
	}

	public void setDown(WeekSchedule down) {
		this.down = down;
	}
}