package com.vitgon.schedule.model.database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.vitgon.schedule.model.database.translation.SchoolTranslation;

import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper=false, of = {"url", "majors"})
public class School extends BaseModel<Integer> {
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "url")
	private String url;
	
	@OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
	private List<Major> majors = new ArrayList<>();
	
	@OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
	private List<SchoolTranslation> translations = new ArrayList<>();
	
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
