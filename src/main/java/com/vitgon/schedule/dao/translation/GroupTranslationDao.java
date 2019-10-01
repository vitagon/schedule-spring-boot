package com.vitgon.schedule.dao.translation;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.database.translation.GroupTranslation;
import com.vitgon.schedule.model.database.translation.pk.GroupTranslationId;

@Repository
public interface GroupTranslationDao extends JpaRepository<GroupTranslation, GroupTranslationId> {

	@Query(value = 
			"SELECT "
			+	"group_id, locale_id, translation " +
			"FROM "
			+	"group_translations " +
			"WHERE "
			+ 	"locale_id = :localeId AND group_id = :groupId",
			nativeQuery = true)
	Optional<GroupTranslation> findByLocaleIdAndGroupId(@Param("localeId") Integer localeId, @Param("groupId") Integer groupId);

	@Modifying
	@Query(value =
			"INSERT INTO group_translations (group_id, locale_id, translation) VALUES "
			+ "(:groupId, :localeId, :translation)",
			nativeQuery = true)
	void save(
			@Param("groupId") Integer groupId,
			@Param("localeId") Integer localeId,
			@Param("translation") String translation);
}
