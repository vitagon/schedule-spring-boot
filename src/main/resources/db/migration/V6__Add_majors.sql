INSERT INTO major (id, name, url, duration, school_id, degree) VALUES (1, 'economics security', 'economics_security', 5, 1, 2);
INSERT INTO major_translations (major_id, locale_id, title) VALUES (1, 1, 'экономическая безопасность');

INSERT INTO major (id, name, url, duration, school_id, degree) VALUES (2, 'management', 'management', 4, 1, 1);
INSERT INTO major_translations (major_id, locale_id, title) VALUES (2, 1, 'менеджмент');

ALTER SEQUENCE major_id_seq RESTART WITH 2;