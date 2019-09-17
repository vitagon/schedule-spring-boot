package com.vitgon.schedule.model.database.translation.pk;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.School;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;


public class SchoolTranslationId implements Serializable {

	private static final long serialVersionUID = 7284414585673531241L;

	@ManyToOne
	@JoinColumn(name = "school_id")
	@JsonBackReference
	private School school;
	
	@ManyToOne
	@JoinColumn(name = "locale_id")
	private Locale locale;

	public SchoolTranslationId() {
		super();
	}

	public SchoolTranslationId(School school, Locale locale) {
		super();
		this.school = school;
		this.locale = locale;
	}

	public School getSchool() {
		return school;
	}

	public Locale getLocale() {
		return locale;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((locale == null) ? 0 : locale.hashCode());
		result = prime * result + ((school == null) ? 0 : school.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SchoolTranslationId other = (SchoolTranslationId) obj;
		if (locale == null) {
			if (other.locale != null)
				return false;
		} else if (!locale.equals(other.locale))
			return false;
		if (school == null) {
			if (other.school != null)
				return false;
		} else if (!school.equals(other.school))
			return false;
		return true;
	}
}
