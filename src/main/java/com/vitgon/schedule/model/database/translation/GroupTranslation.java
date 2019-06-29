package com.vitgon.schedule.model.database.translation;

import javax.persistence.Column;
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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
	
	@Column(name = "suffix_translation", nullable = false)
	public String suffixTranslation;
}
