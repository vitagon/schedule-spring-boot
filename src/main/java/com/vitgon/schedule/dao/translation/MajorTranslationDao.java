package com.vitgon.schedule.dao.translation;

import com.vitgon.schedule.model.database.translation.MajorTranslation;
import com.vitgon.schedule.model.database.translation.pk.MajorTranslationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MajorTranslationDao extends JpaRepository<MajorTranslation, MajorTranslationId> {
	MajorTranslation findByTranslation(String translation);

	@Query(value =
			"SELECT * FROM #{#entityName} WHERE locale_id = :localeId AND major_id = :majorId",
			nativeQuery = true)
	Optional<MajorTranslation> findByLocaleIdAndMajorId(
			@Param("localeId") Integer localeId,
			@Param("majorId") Integer majorId);

	@Modifying
	@Query(value =
			"DELETE FROM #{#entityName} " +
			"WHERE "
			+ "locale_id = :localeId AND major_id = :majorId",
			nativeQuery = true)
    void deleteByMajorIdAndLocaleId(
			@Param("majorId") Integer majorId,
			@Param("localeId") Integer localeId);

    @Modifying
    @Query(value =
			"INSERT INTO #{#entityName} "
			+	"(locale_id, major_id, translation) " +
			"VALUES "
			+	"(:localeId, :majorId, :translation)",
			nativeQuery = true)
    void save(
			@Param("majorId") Integer majorId,
			@Param("localeId") Integer localeId,
			@Param("translation") String translation);
}
