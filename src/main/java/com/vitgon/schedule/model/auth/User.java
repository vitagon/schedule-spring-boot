package com.vitgon.schedule.model.auth;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.vitgon.schedule.model.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "users")
public class User extends BaseModel<Integer> {
	
	@Column(name = "email")
	@Email
	@NotEmpty
	private String email;
	
	@Column(name = "password")
	@Length(min = 5)
	@NotEmpty
	private String password;
	
	@Column(name = "first_name")
	@NotEmpty
	private String firstName;
	
	@Column(name = "last_name")
	@NotEmpty
	private String lastName;
	
	@Column(name = "active")
	private int active;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
}
