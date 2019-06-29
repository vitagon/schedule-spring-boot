package com.vitgon.schedule.model.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vitgon.schedule.dto.Days;
import com.vitgon.schedule.model.database.auth.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"group"})
@Entity
@Table(
	name = "schedules",
	uniqueConstraints = @UniqueConstraint(
		columnNames= {"group_id", "day_num", "week_type", "lesson_num"},
		name = "UQ_schedules_group_id_day_num_week_type_lesson_num"
	)
)
public class Schedule extends BaseModel<Integer> implements Cloneable {
	
	@ManyToOne
	@JoinColumn(name = "subject_id")
	private Subject subject;
	
	@Column(name = "day_num")
	private Integer dayNum;
	
	@Column(name = "week_type", length = 4)
	private String week;
	
	@Column(name = "lesson_num")
	private int lessonNum;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "group_id")
	private Group group;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "lesson_type")
	private int lessonType;
	
	@Column(name = "classroom", length = 6)
	private String classroom;
	
	public Schedule clone() throws CloneNotSupportedException {  
		return (Schedule) super.clone();
	}
}
