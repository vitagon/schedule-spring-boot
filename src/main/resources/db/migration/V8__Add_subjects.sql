INSERT INTO subjects (id, name) VALUES 
	(19, 'enterprise cost management'),
	(20, 'pricing'),
	(21, 'enterprise planning'),
	(22, 'information security'),
	(23, 'economic security'),
	(24, 'criminal process');

ALTER SEQUENCE hibernate_sequence RESTART WITH 25;

INSERT INTO subject_translations (subject_id, locale_id, translation) VALUES
	(19, 1, 'enterprise cost management'),
	(20, 1, 'pricing'),
	(21, 1, 'enterprise planning'),
	(22, 1, 'information security'),
	(23, 1, 'economic security'),
	(24, 1, 'criminal process');

INSERT INTO subject_translations (subject_id, locale_id, translation) VALUES
	(19, 2, 'управление затратами на предприятии'),
	(20, 2, 'ценообразование'),
	(21, 2, 'планирование на предприятии'),
	(22, 2, 'информационная безопасность'),
	(23, 2, 'экономическая безопасность'),
	(24, 2, 'уголовный процесс');