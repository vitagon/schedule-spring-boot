package com.vitgon.schedule.dao.auth;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.database.auth.Role;
import com.vitgon.schedule.model.database.auth.User;

@Repository("userRepository")
public interface UserDao extends JpaRepository<User, Integer> {
	User findByEmail(String email);
	
	@Query("select u from User u inner join u.roles r where r.role in :roles")
	List<User> findBySpecificRoles(@Param("roles") List<String> roles);
}
