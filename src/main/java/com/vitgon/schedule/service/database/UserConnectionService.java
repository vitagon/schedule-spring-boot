package com.vitgon.schedule.service.database;

import com.vitgon.schedule.model.database.auth.UserConnection;
import com.vitgon.schedule.model.database.auth.pk.UserConnectionId;
import com.vitgon.schedule.service.database.base.Service;

public interface UserConnectionService extends Service<UserConnection, UserConnectionId> {
	UserConnection findByProviderIdAndProviderUserId(String providerId, String providerUserId);
}
