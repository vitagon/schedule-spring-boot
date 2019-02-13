package com.vitgon.schedule.model.database.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.vitgon.schedule.model.database.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "roles")
public class Role extends BaseModel<Integer> {
	
	@Column(name = "role")
	private String role;
}
