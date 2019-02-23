package com.vitgon.schedule.model.database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vitgon.schedule.dto.DegreeEnum;
import com.vitgon.schedule.model.database.translation.MajorTranslation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"url", "duration", "school"})
@Entity
@Table(name = "major")
public class Major extends BaseModel<Integer> {
	
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	@Column(name = "url", nullable = false, unique = true)
	private String url;
	
	@Column(name = "duration", nullable = false)
	private int duration;
	
	@JsonBackReference
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "school_id")
	private School school;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "degree", nullable = false)
	private DegreeEnum degree;
	
	@OneToMany(mappedBy = "major", fetch = FetchType.LAZY)
	private List<Group> groups = new ArrayList<>();
	
	@OneToMany(mappedBy = "major", fetch = FetchType.LAZY)
	private List<MajorTranslation> translations = new ArrayList<>();

	public Major(String url, int duration, School school) {
		this.url = url;
		this.duration = duration;
		this.school = school;
	}
}
