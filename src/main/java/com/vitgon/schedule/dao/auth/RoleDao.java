package com.vitgon.schedule.dao.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.database.auth.Role;

@Repository("roleRepository")
public interface RoleDao extends JpaRepository<Role, Integer> {
	Role findByRole(String role);
}
