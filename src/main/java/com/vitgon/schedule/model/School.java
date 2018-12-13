package com.vitgon.schedule.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.vitgon.schedule.model.translation.SchoolTranslation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"majors"})
@Entity
@Table(name = "school")
public class School extends BaseModel<Integer> {
	
	@Column(name = "url")
	private String url;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "school")
	private List<Major> majors = new ArrayList<>();
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "school")
	private List<SchoolTranslation> schoolTranslations = new ArrayList<>();
	
	public School(String url) {
		this.url = url;
	}
	
	public School(Integer id, String url) {
		this.id = id;
		this.url = url;
	}

	public School(String url, List<Major> majors) {
		this.url = url;
		this.majors = majors;
	}
}
