package com.vitgon.schedule.model.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vitgon.schedule.model.database.translation.GroupTranslation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "_groups")
public class Group extends BaseModel<Integer> {
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "course_num", nullable = false)
	private Integer courseNum;
	
	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name="major_id")
	private Major major;
	
	@OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
	private List<Schedule> schedules = new ArrayList<>();
	
	@OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
	private Set<GroupTranslation> groupTranslations = new HashSet<>();
	
	public Group() {
	}

	public Group(int courseNum, Major major) {
		this.courseNum = courseNum;
		this.major = major;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(Integer courseNum) {
		this.courseNum = courseNum;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	public Set<GroupTranslation> getGroupTranslations() {
		return groupTranslations;
	}

	public void setGroupTranslations(Set<GroupTranslation> groupTranslations) {
		this.groupTranslations = groupTranslations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((courseNum == null) ? 0 : courseNum.hashCode());
		result = prime * result + ((groupTranslations == null) ? 0 : groupTranslations.hashCode());
		result = prime * result + ((major == null) ? 0 : major.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((schedules == null) ? 0 : schedules.hashCode());
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
		Group other = (Group) obj;
		if (courseNum == null) {
			if (other.courseNum != null)
				return false;
		} else if (!courseNum.equals(other.courseNum))
			return false;
		if (groupTranslations == null) {
			if (other.groupTranslations != null)
				return false;
		} else if (!groupTranslations.equals(other.groupTranslations))
			return false;
		if (major == null) {
			if (other.major != null)
				return false;
		} else if (!major.equals(other.major))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (schedules == null) {
			if (other.schedules != null)
				return false;
		} else if (!schedules.equals(other.schedules))
			return false;
		return true;
	}
}
