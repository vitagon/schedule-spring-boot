package com.vitgon.schedule.model.translation.pk;

import java.io.Serializable;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.auth.User;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"user", "locale"})
public class UserTranslationId implements Serializable {
	
	private static final long serialVersionUID = -5641639662460763411L;
	
	private User user;
	private Locale locale;
}
