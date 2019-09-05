package com.vitgon.schedule.model.database.translation.pk;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.School;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"school", "locale"})
public class SchoolTranslationId implements Serializable {

	private static final long serialVersionUID = 7284414585673531241L;

	@ManyToOne
	@JoinColumn(name = "school_id")
	@JsonBackReference
	private School school;
	
	@ManyToOne
	@JoinColumn(name = "locale_id")
	private Locale locale;
}
