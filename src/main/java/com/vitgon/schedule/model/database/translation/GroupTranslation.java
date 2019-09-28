package com.vitgon.schedule.model.database.translation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.translation.pk.GroupTranslationId;

import javax.persistence.*;


@Entity
@Table(name = "group_translations")
@IdClass(value = GroupTranslationId.class)
public class GroupTranslation {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "group_id")
	@JsonBackReference
	public Group group;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "locale_id")
	public Locale locale;
	
	@Column(name = "translation", nullable = false)
	public String translation;

	public GroupTranslation() {
		super();
	}

	public GroupTranslation(Group group, Locale locale, String suffixTranslation) {
		super();
		this.group = group;
		this.locale = locale;
		this.suffixTranslation = suffixTranslation;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getSuffixTranslation() {
		return suffixTranslation;
	}

	public void setSuffixTranslation(String suffixTranslation) {
		this.suffixTranslation = suffixTranslation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((locale == null) ? 0 : locale.hashCode());
		result = prime * result + ((suffixTranslation == null) ? 0 : suffixTranslation.hashCode());
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
		GroupTranslation other = (GroupTranslation) obj;
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
		if (suffixTranslation == null) {
			if (other.suffixTranslation != null)
				return false;
		} else if (!suffixTranslation.equals(other.suffixTranslation))
			return false;
		return true;
	}
}
