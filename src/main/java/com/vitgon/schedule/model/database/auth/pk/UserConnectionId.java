package com.vitgon.schedule.model.database.auth.pk;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class UserConnectionId implements Serializable {

	private static final long serialVersionUID = -8867549559315848321L;
	
	private String userId;
	private String providerId;
	private String providerUserId;
	
	public UserConnectionId() {
		super();
	}

	public UserConnectionId(String userId, String providerId, String providerUserId) {
		super();
		this.userId = userId;
		this.providerId = providerId;
		this.providerUserId = providerUserId;
	}
	
	public String getUserId() {
		return userId;
	}
	public String getProviderId() {
		return providerId;
	}
	public String getProviderUserId() {
		return providerUserId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((providerId == null) ? 0 : providerId.hashCode());
		result = prime * result + ((providerUserId == null) ? 0 : providerUserId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		UserConnectionId other = (UserConnectionId) obj;
		if (providerId == null) {
			if (other.providerId != null)
				return false;
		} else if (!providerId.equals(other.providerId))
			return false;
		if (providerUserId == null) {
			if (other.providerUserId != null)
				return false;
		} else if (!providerUserId.equals(other.providerUserId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
}
