package com.vitgon.schedule.model.database.translation;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.translation.pk.GroupTranslationId;


@Entity(name = "group_translations")
@Table(name = "group_translations")
public class GroupTranslation {
	
	@EmbeddedId
	private GroupTranslationId groupTranslationId;
	
	@Column(name = "translation", nullable = false)
	public String translation;

	public GroupTranslation() {
		super();
	}

	public GroupTranslation(Group group, Locale locale, String translation) {
		super();
		this.groupTranslationId = new GroupTranslationId(group, locale);
		this.translation = translation;
	}

	public GroupTranslationId getGroupTranslationId() {
		return groupTranslationId;
	}

	public void setGroupTranslationId(GroupTranslationId groupTranslationId) {
		this.groupTranslationId = groupTranslationId;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groupTranslationId == null) ? 0 : groupTranslationId.hashCode());
		result = prime * result + ((translation == null) ? 0 : translation.hashCode());
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
		if (groupTranslationId == null) {
			if (other.groupTranslationId != null)
				return false;
		} else if (!groupTranslationId.equals(other.groupTranslationId))
			return false;
		if (translation == null) {
			if (other.translation != null)
				return false;
		} else if (!translation.equals(other.translation))
			return false;
		return true;
	}

	
}
