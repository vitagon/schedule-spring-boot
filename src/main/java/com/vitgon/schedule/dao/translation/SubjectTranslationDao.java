package com.vitgon.schedule.dao.translation;

import com.vitgon.schedule.model.database.translation.SubjectTranslation;
import com.vitgon.schedule.model.database.translation.pk.SubjectTranslationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectTranslationDao extends JpaRepository<SubjectTranslation, SubjectTranslationId> {
	SubjectTranslation findByTranslation(String translation);
	
	@Query(value = "SELECT * from #{#entityName}"
					+ "WHERE"
					+    "locale_id = (SELECT id FROM locales WHERE code = :localeCode)"
					+    "AND subject_id = :subjectId",
		   nativeQuery = true)
	Optional<SubjectTranslation> findByLocaleCodeAndSubjectId(
			@Param("localeCode") String localeCode,
			@Param("subjectId") Integer subjectId
	);
	
	@Query(value = "SELECT * from #{#entityName} WHERE locale_id = :localeId AND subject_id = :subjectId",
		   nativeQuery = true)
	Optional<SubjectTranslation> findByLocaleIdAndSubjectId(
			@Param("localeId") Integer localeId,
			@Param("subjectId") Integer subjectId
	);
	
	@Modifying
	@Query(value = "INSERT INTO #{#entityName} (subject_id, locale_id, translation) "
					+ "VALUES (:subjectId, :localeId, :translation)", 
		   nativeQuery = true)
	Integer save(@Param("subjectId") Integer subjectId,
				 @Param("localeId") Integer localeId,
				 @Param("translation") String translation);
	
	@Modifying
	@Query(value = "DELETE FROM #{#entityName} "
					+ "WHERE"
					+ 	" subject_id = :subjectId AND"
					+ 	" locale_id  = :localeId",
		   nativeQuery = true)
	void deleteBySubjectIdAndLocaleId(@Param("subjectId") Integer subjectId,
									  @Param("localeId") Integer localeId);
}
