INSERT INTO majors (id, name, url, duration, school_id, degree) VALUES (15, 'economics security', 'economics_security', 5, 12, 2);
INSERT INTO major_translations (major_id, locale_id, title) VALUES (15, 2, 'экономическая безопасность');

INSERT INTO majors (id, name, url, duration, school_id, degree) VALUES (16, 'management', 'management', 4, 12, 1);
INSERT INTO major_translations (major_id, locale_id, title) VALUES (16, 2, 'менеджмент');

ALTER SEQUENCE hibernate_sequence RESTART WITH 17;