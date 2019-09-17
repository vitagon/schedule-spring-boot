package com.vitgon.schedule.dao.translation;

import com.vitgon.schedule.model.database.translation.SchoolTranslation;
import com.vitgon.schedule.model.database.translation.pk.SchoolTranslationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolTranslationDao extends JpaRepository<SchoolTranslation, SchoolTranslationId> {
	SchoolTranslation findByTranslation(String translation);
	
	@Query(value = "SELECT * from #{#entityName} WHERE locale_id = :localeId AND school_id = :schoolId",
			   nativeQuery = true)
	Optional<SchoolTranslation> findByLocaleIdAndSchoolId(
			@Param("localeId") Integer localeId,
			@Param("schoolId") Integer schoolId
	);
	
	@Modifying
	@Query(value =
				"INSERT" +
				"	INTO #{#entityName} (school_id, locale_id, translation) " +
				"VALUES "
				+	"(:schoolId, :localeId, :translation)",
			nativeQuery = true)
	Integer save(
			@Param("schoolId") Integer schoolId,
			@Param("localeId") Integer localeId,
			@Param("translation") String translation
	);
	
	@Modifying
	@Query(value =
				"DELETE FROM #{#entityName} " +
				"WHERE "
				+	" school_id = :schoolId AND"
				+	" locale_id = :localeId",
			nativeQuery = true)
	void deleteBySchoolIdAndLocaleId(
			@Param("schoolId") Integer schoolId,
			@Param("localeId") Integer localeId
	);
}
