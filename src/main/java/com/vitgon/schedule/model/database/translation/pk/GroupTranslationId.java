package com.vitgon.schedule.model.database.translation.pk;

import java.io.Serializable;

import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.model.database.Locale;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class GroupTranslationId implements Serializable {

	private static final long serialVersionUID = 7291286878906686742L;
	
	public Group group;
	public Locale locale;
	
	public GroupTranslationId() {
		super();
	}

	public GroupTranslationId(Group group, Locale locale) {
		super();
		this.group = group;
		this.locale = locale;
	}
	
	public Group getGroup() {
		return group;
	}
	public Locale getLocale() {
		return locale;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((locale == null) ? 0 : locale.hashCode());
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
		GroupTranslationId other = (GroupTranslationId) obj;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (locale == null) {
			if (other.locale != null)
				return false;
		} else if (!locale.equals(other.locale))
			return false;
		return true;
	}
}
