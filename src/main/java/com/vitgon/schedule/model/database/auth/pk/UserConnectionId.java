package com.vitgon.schedule.model.database.auth.pk;

import java.io.Serializable;

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
	
	private String userId;
	private String providerId;
	private String providerUserId;
}
