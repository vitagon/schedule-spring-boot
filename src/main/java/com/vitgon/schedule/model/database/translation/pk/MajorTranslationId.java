package com.vitgon.schedule.model.database.translation.pk;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Major;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;


public class MajorTranslationId implements Serializable {
	
	private static final long serialVersionUID = 4322466337734164549L;

	@ManyToOne
	@JoinColumn(name = "major_id")
	@JsonBackReference
	private Major major;

	@ManyToOne
	@JoinColumn(name = "locale_id")
	private Locale locale;
	
	public MajorTranslationId() {
		super();
	}

	public MajorTranslationId(Major major, Locale locale) {
		super();
		this.major = major;
		this.locale = locale;
	}
	
	public Major getMajor() {
		return major;
	}
	
	public Locale getLocale() {
		return locale;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((locale == null) ? 0 : locale.hashCode());
		result = prime * result + ((major == null) ? 0 : major.hashCode());
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
		MajorTranslationId other = (MajorTranslationId) obj;
		if (locale == null) {
			if (other.locale != null)
				return false;
		} else if (!locale.equals(other.locale))
			return false;
		if (major == null) {
			if (other.major != null)
				return false;
		} else if (!major.equals(other.major))
			return false;
		return true;
	}
}
