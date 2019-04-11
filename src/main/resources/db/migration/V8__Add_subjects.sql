INSERT INTO subjects (id, name) VALUES (1, 'enterprise cost management');
INSERT INTO subjects (id, name) VALUES (2, 'pricing');
INSERT INTO subjects (id, name) VALUES (3, 'enterprise planning');
INSERT INTO subjects (id, name) VALUES (4, 'information security');
INSERT INTO subjects (id, name) VALUES (5, 'economic security');
INSERT INTO subjects (id, name) VALUES (6, 'criminal process');

ALTER SEQUENCE subjects_id_seq RESTART WITH 6;

INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (1, 1, 'управление затратами на предприятии');
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (2, 1, 'ценообразование');
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (3, 1, 'планирование на предприятии');
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (4, 1, 'информационная безопасность');
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (5, 1, 'экономическая безопасность');
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (6, 1, 'уголовный процесс');