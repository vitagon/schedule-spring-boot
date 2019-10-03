INSERT INTO majors (id, name, url, duration, school_id, degree) VALUES
  (15, 'economics security', 'economics_security', 5, 12, 2),
  (16, 'management', 'management', 4, 12, 1);

ALTER SEQUENCE hibernate_sequence RESTART WITH 17;

INSERT INTO major_translations (major_id, locale_id, translation) VALUES
	(15, 1, 'economics security'),
  (15, 2, 'экономическая безопасность'),
	(16, 1, 'management'),
  (16, 2, 'менеджмент');