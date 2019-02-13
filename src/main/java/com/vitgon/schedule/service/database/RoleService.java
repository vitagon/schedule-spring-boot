package com.vitgon.schedule.service.database;

import com.vitgon.schedule.model.database.auth.Role;
import com.vitgon.schedule.service.database.base.Service;

public interface RoleService extends Service<Role, Integer> {
	Role findByRole(String role);
}
