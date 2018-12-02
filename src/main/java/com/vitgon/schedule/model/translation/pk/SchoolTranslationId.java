package com.vitgon.schedule.model.translation.pk;

import java.io.Serializable;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.School;

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

	private School school;
	private Locale locale;
}
