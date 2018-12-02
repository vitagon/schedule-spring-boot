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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vitgon.schedule.model.translation.MajorTranslation;

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
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "duration")
	private int duration;
	
	@JsonBackReference
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "school_id")
	private School school;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "major")
	private List<Group> groups = new ArrayList<>();
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "major")
	private List<MajorTranslation> majorTranslations = new ArrayList<>();

	public Major(String url, int duration, School school) {
		this.url = url;
		this.duration = duration;
		this.school = school;
	}
}
