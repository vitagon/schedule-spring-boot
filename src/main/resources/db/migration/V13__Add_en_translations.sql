INSERT INTO user_translations (user_id, locale_id, lastname, firstname, middlename) VALUES
	(8, 1, 'efremova','polina','vitalyevna'),
	(9, 1, 'vorobyeva','larisa','gennadyevna'),
	(10, 1, 'mishunina','lidia','nikolaevna'),
	(11, 1, 'karpets','olga','viktorovna');
	
INSERT INTO school_translations (school_id, locale_id, translation) VALUES
	(12, 1, 'school of economics and management'),
	(13, 1, 'school of natural sciences'),
	(14, 1, 'law school');
	
INSERT INTO major_translations (major_id, locale_id, translation) VALUES
	(15, 1, 'economics security'),
	(16, 1, 'management');

INSERT INTO group_translations (group_id, locale_id, suffix_translation) VALUES
	(17, 1, 'pd');
	
INSERT INTO group_translations (group_id, locale_id, suffix_translation) VALUES
	(17, 2, 'пд');
	
INSERT INTO subject_translations (subject_id, locale_id, translation) VALUES
	(18, 1, 'enterprise cost management'),
	(19, 1, 'pricing'),
	(20, 1, 'enterprise planning'),
	(21, 1, 'information security'),
	(22, 1, 'economic security'),
	(23, 1, 'criminal process');