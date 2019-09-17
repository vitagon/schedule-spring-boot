package com.vitgon.schedule.dao.translation;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.model.database.translation.UserTranslation;
import com.vitgon.schedule.model.database.translation.pk.UserTranslationId;
import com.vitgon.schedule.projection.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTranslationDao extends JpaRepository<UserTranslation, UserTranslationId> {
	UserTranslation findByUserTranslationIdLocaleAndUserTranslationIdUser(Locale locale, User user);
	
	@Query(value = 
			"SELECT "
			+ 	"us.id, usTr.firstname, usTr.lastname, usTr.middlename, "
			+ 	"us.key_firstname, us.key_lastname, us.key_middlename " +
			"FROM "
			+ 	"user_translations usTr " +
			"JOIN "
			+ 	"users us ON usTr.user_id = us.id " +
			"WHERE "
			+	"usTr.locale_id = ?1 "
			+	"AND "
			+ 	"usTr.user_id = ?2",
			nativeQuery = true)
	UserProjection getUserTranslationProjectionByLocaleIdAndUserId(Integer localeId, Integer userId);
	
	@Query(value = "SELECT * FROM user_translations WHERE locale_id = ?1 AND user_id = ?2",
		   nativeQuery = true)
	Optional<UserTranslation> findByLocaleIdAndUserId(Integer localeId, Integer userId);
}
