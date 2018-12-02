package com.vitgon.schedule.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vitgon.schedule.model.translation.TeacherTranslation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"schedules"})
@Entity
@Table(name = "teacher")
public class Teacher extends BaseModel<Integer> {
	
	@Column(name = "mail")
	private String mail;
	
	@JsonIgnore
	@OneToMany(mappedBy = "teacher")
	private List<Schedule> schedules = new ArrayList<>();
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "teacher")
	private List<TeacherTranslation> teacherTranslations = new ArrayList<>();

	public Teacher(String mail, List<Schedule> schedules) {
		this.mail = mail;
		this.schedules = schedules;
	}
}
