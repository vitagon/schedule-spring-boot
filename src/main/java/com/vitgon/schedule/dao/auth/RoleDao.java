package com.vitgon.schedule.dao.auth;

import com.vitgon.schedule.model.database.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleDao extends JpaRepository<Role, Integer> {
	Role findByRole(String role);
}
