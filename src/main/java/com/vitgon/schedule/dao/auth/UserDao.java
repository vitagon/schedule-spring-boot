package com.vitgon.schedule.dao.auth;

import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.projection.UserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public interface UserDao extends JpaRepository<User, Integer> {
	List<User> findByEmail(String email);
	User findByEmailAndProviderId(String email, String providerId);
	User findByUsername(String username);
	
	@Query("select u from User u where u.lastname LIKE %?1% AND u.firstname LIKE %?2% AND u.middlename LIKE %?3%")
	List<User> findByLastnameAndFirstnameAndMiddlename(String lastname, String firstname, String middlename);
	
	@Query("select u from User u where u.lastname LIKE %?1% AND u.firstname LIKE %?2%")
	List<User> findByLastnameAndFirstname(String lastname, String firstname);
	
	@Query("select u from User u where u.firstname LIKE %?1% AND u.middlename LIKE %?2%")
	List<User> findByFirstnameAndMiddlename(String firstname, String middlename);
	
	@Query("select u from User u where u.lastname LIKE %?1%")
	List<User> findByLastname(String lastname);
	
	@Query("select u from User u inner join u.roles r where r.role in :roles")
	List<User> findBySpecificRoles(@Param("roles") List<String> roles);
	
	
	@Query("select u from User u inner join u.roles r where r.role = :role")
	Page<User> findByRole(@Param("role") String role, Pageable pageable);
	
	@Query("select u from User u inner join u.roles r where r.role = :role")
	List<User> findByRole(String role);
}
