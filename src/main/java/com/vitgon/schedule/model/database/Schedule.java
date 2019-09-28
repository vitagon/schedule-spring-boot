package com.vitgon.schedule.model.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vitgon.schedule.model.database.auth.User;

import javax.persistence.*;


@Entity
@Table(
	name = "schedules",
	uniqueConstraints = @UniqueConstraint(
		columnNames= {"group_id", "subgroup_id", "day_num", "week_type", "lesson_num"},
		name = "UQ_schedules_group_id_subgroup_id_day_num_week_type_lesson_num"
	)
)
public class Schedule extends BaseModel<Integer> implements Cloneable {
	
	@ManyToOne
	@JoinColumn(name = "subject_id", nullable = false)
	private Subject subject;
	
	@Column(name = "day_num", nullable = false)
	private Integer dayNum;
	
	@Column(name = "week_type", length = 4, nullable = false)
	private String week;
	
	@Column(name = "lesson_num", nullable = false)
	private int lessonNum;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "group_id", nullable = false)
	private Group group;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "lesson_type", nullable = false)
	private int lessonType;
	
	@Column(name = "classroom", length = 6, nullable = false)
	private String classroom;

	@Column(name = "subgroup_id", nullable = true)
	private Integer subgroupId;
	
	public Schedule() {
		super();
	}

	public Schedule clone() throws CloneNotSupportedException {  
		return (Schedule) super.clone();
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Integer getDayNum() {
		return dayNum;
	}

	public void setDayNum(Integer dayNum) {
		this.dayNum = dayNum;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public int getLessonNum() {
		return lessonNum;
	}

	public void setLessonNum(int lessonNum) {
		this.lessonNum = lessonNum;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getLessonType() {
		return lessonType;
	}

	public void setLessonType(int lessonType) {
		this.lessonType = lessonType;
	}

	public String getClassroom() {
		return classroom;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

	public Integer getSubgroupId() {
		return subgroupId;
	}

	public void setSubgroupId(Integer subgroupId) {
		this.subgroupId = subgroupId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((classroom == null) ? 0 : classroom.hashCode());
		result = prime * result + ((dayNum == null) ? 0 : dayNum.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + lessonNum;
		result = prime * result + lessonType;
		result = prime * result + ((subgroupId == null) ? 0 : subgroupId.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((week == null) ? 0 : week.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		if (classroom == null) {
			if (other.classroom != null)
				return false;
		} else if (!classroom.equals(other.classroom))
			return false;
		if (dayNum == null) {
			if (other.dayNum != null)
				return false;
		} else if (!dayNum.equals(other.dayNum))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (lessonNum != other.lessonNum)
			return false;
		if (lessonType != other.lessonType)
			return false;
		if (subgroupId == null) {
			if (other.subgroupId != null)
				return false;
		} else if (!subgroupId.equals(other.subgroupId))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (week == null) {
			if (other.week != null)
				return false;
		} else if (!week.equals(other.week))
			return false;
		return true;
	}
}
