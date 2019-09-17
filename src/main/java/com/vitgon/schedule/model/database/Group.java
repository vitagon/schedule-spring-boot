package com.vitgon.schedule.model.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vitgon.schedule.model.database.translation.GroupTranslation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(
		name = "_groups",
		uniqueConstraints = @UniqueConstraint(
			columnNames = {"major_id","number","suffix"},
			name = "UQ__groups_major_id_number"
		)	
)
public class Group extends BaseModel<Integer> {
	
	@Column(name = "number", nullable = false)
	private Integer number;
	
	@Column(name = "suffix", nullable = false)
	private String suffix;
	
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

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
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
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((schedules == null) ? 0 : schedules.hashCode());
		result = prime * result + ((suffix == null) ? 0 : suffix.hashCode());
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
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (schedules == null) {
			if (other.schedules != null)
				return false;
		} else if (!schedules.equals(other.schedules))
			return false;
		if (suffix == null) {
			if (other.suffix != null)
				return false;
		} else if (!suffix.equals(other.suffix))
			return false;
		return true;
	}
}
