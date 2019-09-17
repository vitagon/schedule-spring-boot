package com.vitgon.schedule.model.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vitgon.schedule.model.database.translation.SubjectTranslation;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "subjects")
public class Subject extends BaseModel<Integer> {
	
	@Column(name = "name")
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "subject")
	private List<Schedule> schedules = new ArrayList<>();
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "subjectTranslationId.subject")
	private List<SubjectTranslation> translations = new ArrayList<>();
	
	public Subject() {
		super();
	}

	public Subject(String name) {
		this.name = name;
	}

	public Subject(List<Schedule> schedules, List<SubjectTranslation> translations) {
		this.schedules = schedules;
		this.translations = translations;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	public List<SubjectTranslation> getTranslations() {
		return translations;
	}

	public void setTranslations(List<SubjectTranslation> translations) {
		this.translations = translations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((schedules == null) ? 0 : schedules.hashCode());
		result = prime * result + ((translations == null) ? 0 : translations.hashCode());
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
		Subject other = (Subject) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (schedules == null) {
			if (other.schedules != null)
				return false;
		} else if (!schedules.equals(other.schedules))
			return false;
		if (translations == null) {
			if (other.translations != null)
				return false;
		} else if (!translations.equals(other.translations))
			return false;
		return true;
	}
}
