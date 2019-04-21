package com.vitgon.schedule.dao.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.database.auth.UserConnection;
import com.vitgon.schedule.model.database.auth.pk.UserConnectionId;

@Repository
public interface UserConnectionDao extends JpaRepository<UserConnection, UserConnectionId> {
	UserConnection findByProviderIdAndProviderUserId(String providerId, String providerUserId);
}
