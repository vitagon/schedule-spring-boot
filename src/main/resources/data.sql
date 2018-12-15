INSERT INTO locales (id, code) VALUES (1, 'ru')
INSERT INTO locales (id, code) VALUES (2, 'en')

INSERT INTO school (id, url) VALUES (1, 'school_of_economics_and_management')
INSERT INTO school_translations (school_id, locale_id, title) VALUES (1, 1, 'школа экономики и менеджмента')
INSERT INTO school_translations (school_id, locale_id, title) VALUES (1, 2, 'school of economics and management')

INSERT INTO major (id, url, duration, school_id) VALUES (1, 'economics_security', 5, 1)
INSERT INTO major_translations (major_id, locale_id, title) VALUES (1, 1, 'экономическая безопасность')
INSERT INTO major_translations (major_id, locale_id, title) VALUES (1, 2, 'economics security')

INSERT INTO major (id, url, duration, school_id) VALUES (2, 'management', 4, 1)
INSERT INTO major_translations (major_id, locale_id, title) VALUES (2, 1, 'менеджмент')
INSERT INTO major_translations (major_id, locale_id, title) VALUES (2, 2, 'management')

INSERT INTO school (id, url) VALUES (2, 'school_of_natural_sciences')
INSERT INTO school_translations (school_id, locale_id, title) VALUES (2, 1, 'школа eстественных наук')
INSERT INTO school_translations (school_id, locale_id, title) VALUES (2, 2, 'school of natural sciences')

INSERT INTO school (id, url) VALUES (3, 'law_school')
INSERT INTO school_translations (school_id, locale_id, title) VALUES (3, 1, 'школа юридических наук')
INSERT INTO school_translations (school_id, locale_id, title) VALUES (3, 2, 'law school')

INSERT INTO groups (course_num, major_id) VALUES (4, 1)
INSERT INTO group_translations (group_id, locale_id, title) VALUES (1,1,'С1401пд')
INSERT INTO group_translations (group_id, locale_id, title) VALUES (1,2,'S1401pd')