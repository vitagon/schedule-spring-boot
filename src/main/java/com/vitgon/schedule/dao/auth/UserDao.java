package com.vitgon.schedule.dao.auth;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.projection.UserProjection;

@Repository("userRepository")
public interface UserDao extends JpaRepository<User, Integer> {
	List<User> findByEmail(String email);
	User findByEmailAndProviderId(String email, String providerId);
	User findByUsername(String username);
	
	@Query("select u from User u where u.keyLastname LIKE %?1% AND u.keyFirstname LIKE %?2% AND u.keyMiddlename LIKE %?3%")
	List<User> findByLastnameAndFirstnameAndMiddlename(String lastname, String firstname, String middlename);
	
	@Query("select u from User u where u.keyLastname LIKE %?1% AND u.keyFirstname LIKE %?2%")
	List<User> findByLastnameAndFirstname(String lastname, String firstname);
	
	@Query("select u from User u where u.keyFirstname LIKE %?1% AND u.keyMiddlename LIKE %?2%")
	List<User> findByFirstnameAndMiddlename(String firstname, String middlename);
	
	@Query("select u from User u where u.keyLastname LIKE %?1%")
	List<User> findByLastname(String lastname);
	
	@Query("select u from User u inner join u.roles r where r.role in :roles")
	List<User> findBySpecificRoles(@Param("roles") List<String> roles);
	
	
	@Query(value =
			"SELECT "
			+ 	"us.id, usTr.firstname, usTr.lastname, usTr.middlename, "
			+ 	"us.key_firstname, us.key_lastname, us.key_middlename " +
			"FROM "
			+	"users us " +
			"JOIN "
			+	"user_role ur ON us.id = ur.user_id " +
			"JOIN "
			+	"user_translations usTr ON us.id = usTr.user_id " +
			"WHERE "
			+ 	"usTr.locale_id = ?1 "
			+ 	"AND "
			+ 	"ur.role_id = (SELECT id FROM roles WHERE role = upper(?2))",
			nativeQuery = true)
	Page<UserProjection> getAllUsersByLocaleIdAndRoleJoiningWithTranslation(Integer localeId, String role, Pageable pageable);
}
