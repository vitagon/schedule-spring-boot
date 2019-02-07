package com.vitgon.schedule.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vitgon.schedule.model.auth.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"group"})
@Entity
@Table(name = "schedules",
	   uniqueConstraints = @UniqueConstraint(
			   columnNames= {"group_id", "day_num", "week_type", "lesson_num"},
			   name = "schedule_UI")
)
public class Schedule extends BaseModel<Integer> {
	
	@ManyToOne
	@JoinColumn(name = "subject_id")
	private Subject subject;
	
	@Column(name = "day_num")
	private int dayNum;
	
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
}
