package com.vitgon.schedule.model.database.auth;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.vitgon.schedule.model.database.BaseModel;
import com.vitgon.schedule.model.database.translation.UserTranslation;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "users")
public class User extends BaseModel<Integer> {

	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	@Column(name = "email")
	@Email(message = "{Email.user.email}")
	@NotEmpty(message = "{NotEmpty.default}")
	private String email;
	
	@Column(name = "password", nullable = false)
	@Length(min = 5, message = "{Length.user.password}")
	@NotEmpty(message = "{NotEmpty.default}")
	private String password;
	
	@Column(name = "key_firstname")
	private String keyFirstname;
	
	@Column(name = "key_lastname")
	private String keyLastname;
	
	@Column(name = "key_middlename")
	private String keyMiddlename;
	
	@Column(name = "birth")
	private Date birth;
	
	@Column(name = "active", nullable = false)
	private boolean active;
	
	@Column(name = "provider_id", nullable = false)
	private String providerId;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	@OneToMany(mappedBy = "userTranslationId.user")
	private List<UserTranslation> translations = new ArrayList<>();
	
	public User() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getKeyFirstname() {
		return keyFirstname;
	}

	public void setKeyFirstname(String keyFirstname) {
		this.keyFirstname = keyFirstname;
	}

	public String getKeyLastname() {
		return keyLastname;
	}

	public void setKeyLastname(String keyLastname) {
		this.keyLastname = keyLastname;
	}

	public String getKeyMiddlename() {
		return keyMiddlename;
	}

	public void setKeyMiddlename(String keyMiddlename) {
		this.keyMiddlename = keyMiddlename;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public List<UserTranslation> getTranslations() {
		return translations;
	}

	public void setTranslations(List<UserTranslation> translations) {
		this.translations = translations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((birth == null) ? 0 : birth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((keyFirstname == null) ? 0 : keyFirstname.hashCode());
		result = prime * result + ((keyLastname == null) ? 0 : keyLastname.hashCode());
		result = prime * result + ((keyMiddlename == null) ? 0 : keyMiddlename.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((providerId == null) ? 0 : providerId.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((translations == null) ? 0 : translations.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (active != other.active)
			return false;
		if (birth == null) {
			if (other.birth != null)
				return false;
		} else if (!birth.equals(other.birth))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (keyFirstname == null) {
			if (other.keyFirstname != null)
				return false;
		} else if (!keyFirstname.equals(other.keyFirstname))
			return false;
		if (keyLastname == null) {
			if (other.keyLastname != null)
				return false;
		} else if (!keyLastname.equals(other.keyLastname))
			return false;
		if (keyMiddlename == null) {
			if (other.keyMiddlename != null)
				return false;
		} else if (!keyMiddlename.equals(other.keyMiddlename))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (providerId == null) {
			if (other.providerId != null)
				return false;
		} else if (!providerId.equals(other.providerId))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (translations == null) {
			if (other.translations != null)
				return false;
		} else if (!translations.equals(other.translations))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
