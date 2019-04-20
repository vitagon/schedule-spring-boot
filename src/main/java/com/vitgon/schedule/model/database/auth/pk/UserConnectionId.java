package com.vitgon.schedule.model.database.auth.pk;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"userId", "providerId", "providerUserId"})
public class UserConnectionId implements Serializable {

	private static final long serialVersionUID = -8867549559315848321L;
	
	@Column(name = "userId", length = 255, nullable = false)
	private String userId;
	
	@Column(name = "providerId", length = 255, nullable = false)
	private String providerId;
	
	@Column(name = "providerUserId", length = 255, nullable = false)
	private String providerUserId;
}
