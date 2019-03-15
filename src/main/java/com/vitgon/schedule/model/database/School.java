package com.vitgon.schedule.model.database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.vitgon.schedule.model.database.translation.SchoolTranslation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"majors"})
@Entity
@Table(name = "school")
@EqualsAndHashCode(callSuper=false, of = {"url", "majors"})
public class School extends BaseModel<Integer> {
	
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	@Column(name = "url", nullable = false, unique = true)
	private String url;
	
	@OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
	private List<Major> majors = new ArrayList<>();
	
	@OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
	private List<SchoolTranslation> translations = new ArrayList<>();
	
	public School(String url) {
		this.url = url;
	}

	public School(String name, String url) {
		this.name = name;
		this.url = url;
	}
}
