package com.vitgon.schedule.model.translation.pk;

import java.io.Serializable;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Teacher;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"teacher", "locale"})
public class TeacherTranslationId implements Serializable {
	
	private static final long serialVersionUID = -5641639662460763411L;
	
	private Teacher teacher;
	private Locale locale;
}
