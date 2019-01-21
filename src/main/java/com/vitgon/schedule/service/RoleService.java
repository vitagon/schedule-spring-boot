package com.vitgon.schedule.service;

import com.vitgon.schedule.model.auth.Role;
import com.vitgon.schedule.service.base.Service;

public interface RoleService extends Service<Role, Integer> {
	Role findByRole(String role);
}
