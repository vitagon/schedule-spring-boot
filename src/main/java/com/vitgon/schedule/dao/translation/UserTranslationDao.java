package com.vitgon.schedule.dao.translation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.model.database.translation.UserTranslation;
import com.vitgon.schedule.model.database.translation.pk.UserTranslationId;

@Repository
public interface UserTranslationDao extends JpaRepository<UserTranslation, UserTranslationId> {
	UserTranslation findByUserTranslationIdLocaleAndUserTranslationIdUser(Locale locale, User user);
}
