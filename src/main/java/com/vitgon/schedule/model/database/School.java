package com.vitgon.schedule.model.database;

import com.vitgon.schedule.model.database.translation.SchoolTranslation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(
	name = "schools",
	uniqueConstraints = @UniqueConstraint(
		columnNames= {"name", "url"},
		name = "UQ_school_name_url"
	)
)
public class School extends BaseModel<Integer> {
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "url", nullable = false)
	private String url;
	
	@OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
	private List<Major> majors = new ArrayList<>();
	
	@OneToMany(mappedBy = "schoolTranslationId.school", fetch = FetchType.LAZY)
	private List<SchoolTranslation> translations = new ArrayList<>();
	
	public School() {
	}
	
	public School(String url) {
		this.url = url;
	}

	public School(String name, String url) {
		this.name = name;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Major> getMajors() {
		return majors;
	}

	public void setMajors(List<Major> majors) {
		this.majors = majors;
	}

	public List<SchoolTranslation> getTranslations() {
		return translations;
	}

	public void setTranslations(List<SchoolTranslation> translations) {
		this.translations = translations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((majors == null) ? 0 : majors.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((translations == null) ? 0 : translations.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		School other = (School) obj;
		if (majors == null) {
			if (other.majors != null)
				return false;
		} else if (!majors.equals(other.majors))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (translations == null) {
			if (other.translations != null)
				return false;
		} else if (!translations.equals(other.translations))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
}
