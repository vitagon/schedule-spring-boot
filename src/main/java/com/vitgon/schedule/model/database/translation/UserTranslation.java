package com.vitgon.schedule.model.database.translation;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.vitgon.schedule.model.database.translation.pk.UserTranslationId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_translations")
public class UserTranslation {
	
	@EmbeddedId
	private UserTranslationId userTranslationId;
	
	@Column(name = "lastname")
	private String lastname;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "middlename")
	private String middlename;
}
