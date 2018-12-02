package com.vitgon.schedule.model.translation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vitgon.schedule.model.Group;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.translation.pk.GroupTranslationId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"group"})
@Entity
@Table(name = "group_translations")
@IdClass(GroupTranslationId.class)
public class GroupTranslation {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "group_id")
	@JsonBackReference
	private Group group;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "locale_id")
	private Locale locale;
	
	@Column(name = "title")
	private String title;

	public GroupTranslation(Group group, Locale locale, String title) {
		this.group = group;
		this.locale = locale;
		this.title = title;
	}
}
