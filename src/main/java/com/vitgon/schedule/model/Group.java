package com.vitgon.schedule.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vitgon.schedule.model.translation.GroupTranslation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"courseNum", "groupTranslations"})
@Entity
@Table(name = "_groups")
public class Group extends BaseModel<Integer> {
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "course_num")
	private int courseNum;
	
	@JsonIgnore
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name="major_id")
	private Major major;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
	private List<GroupTranslation> groupTranslations = new ArrayList<>();
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "group")
	private List<Schedule> schedules = new ArrayList<>();

	public Group(int courseNum, Major major) {
		this.courseNum = courseNum;
		this.major = major;
	}
}
