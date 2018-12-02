package com.vitgon.schedule.model.translation.pk;

import java.io.Serializable;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Major;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"major", "locale"})
public class MajorTranslationId implements Serializable {
	
	private static final long serialVersionUID = 4322466337734164549L;

	private Major major;
	private Locale locale;
}
