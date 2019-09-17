package com.vitgon.schedule.dao.auth;

import com.vitgon.schedule.model.database.auth.UserConnection;
import com.vitgon.schedule.model.database.auth.pk.UserConnectionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserConnectionDao extends JpaRepository<UserConnection, UserConnectionId> {
	UserConnection findByProviderIdAndProviderUserId(String providerId, String providerUserId);
}
