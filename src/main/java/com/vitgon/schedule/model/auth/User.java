package com.vitgon.schedule.model.auth;

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

import com.vitgon.schedule.model.BaseModel;
import com.vitgon.schedule.model.translation.UserTranslation;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "users")
public class User extends BaseModel<Integer> {
	
	@Column(name = "email")
	@Email(message = "{Email.user.email}")
	@NotEmpty(message = "{NotEmpty.default}")
	private String email;
	
	@Column(name = "password")
	@Length(min = 5, message = "{Length.user.password}")
	@NotEmpty(message = "{NotEmpty.default}")
	private String password;
	
	@Column(name = "key_firstname")
	@NotEmpty(message = "{NotEmpty.default}")
	private String keyFirstname;
	
	@Column(name = "key_lastname")
	@NotEmpty(message = "{NotEmpty.default}")
	private String keyLastname;
	
	@Column(name = "key_middlename")
	@NotEmpty(message = "{NotEmpty.default}")
	private String keyMiddlename;
	
	@Column(name = "birth")
	@NotNull(message = "{NotNull.default}")
	private Date birth;
	
	@Column(name = "active")
	private int active;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	@OneToMany(mappedBy = "userTranslationId.user")
	private List<UserTranslation> translations = new ArrayList<>();
}
