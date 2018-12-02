package com.vitgon.schedule.model.translation.pk;

import java.io.Serializable;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Subject;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"subject", "locale"})
public class SubjectTranslationId implements Serializable {
	
	private static final long serialVersionUID = -7802327655330293030L;
	
	private Subject subject;
	private Locale locale;
}
