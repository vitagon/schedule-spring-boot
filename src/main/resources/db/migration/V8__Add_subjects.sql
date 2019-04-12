INSERT INTO subjects (id, name) VALUES (18, 'enterprise cost management');
INSERT INTO subjects (id, name) VALUES (19, 'pricing');
INSERT INTO subjects (id, name) VALUES (20, 'enterprise planning');
INSERT INTO subjects (id, name) VALUES (21, 'information security');
INSERT INTO subjects (id, name) VALUES (22, 'economic security');
INSERT INTO subjects (id, name) VALUES (23, 'criminal process');

ALTER SEQUENCE hibernate_sequence RESTART WITH 24;

INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (18, 2, 'управление затратами на предприятии');
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (19, 2, 'ценообразование');
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (20, 2, 'планирование на предприятии');
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (21, 2, 'информационная безопасность');
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (22, 2, 'экономическая безопасность');
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (23, 2, 'уголовный процесс');