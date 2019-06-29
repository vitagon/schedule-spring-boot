INSERT INTO subjects (id, name) VALUES 
	(18, 'enterprise cost management'),
	(19, 'pricing'),
	(20, 'enterprise planning'),
	(21, 'information security'),
	(22, 'economic security'),
	(23, 'criminal process');

ALTER SEQUENCE hibernate_sequence RESTART WITH 24;

INSERT INTO subject_translations (subject_id, locale_id, translation) VALUES
	(18, 2, 'управление затратами на предприятии'),
	(19, 2, 'ценообразование'),
	(20, 2, 'планирование на предприятии'),
	(21, 2, 'информационная безопасность'),
	(22, 2, 'экономическая безопасность'),
	(23, 2, 'уголовный процесс');