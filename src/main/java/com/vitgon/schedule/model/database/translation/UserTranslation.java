package com.vitgon.schedule.model.database.translation;

import com.vitgon.schedule.model.database.translation.pk.UserTranslationId;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


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

	public UserTranslation() {
		super();
	}

	public UserTranslation(UserTranslationId userTranslationId, String lastname, String firstname, String middlename) {
		super();
		this.userTranslationId = userTranslationId;
		this.lastname = lastname;
		this.firstname = firstname;
		this.middlename = middlename;
	}

	public UserTranslationId getUserTranslationId() {
		return userTranslationId;
	}

	public void setUserTranslationId(UserTranslationId userTranslationId) {
		this.userTranslationId = userTranslationId;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((middlename == null) ? 0 : middlename.hashCode());
		result = prime * result + ((userTranslationId == null) ? 0 : userTranslationId.hashCode());
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
		UserTranslation other = (UserTranslation) obj;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (middlename == null) {
			if (other.middlename != null)
				return false;
		} else if (!middlename.equals(other.middlename))
			return false;
		if (userTranslationId == null) {
			if (other.userTranslationId != null)
				return false;
		} else if (!userTranslationId.equals(other.userTranslationId))
			return false;
		return true;
	}
}
