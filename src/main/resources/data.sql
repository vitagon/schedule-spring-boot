INSERT INTO roles (id, role) VALUES (1,'USER')
INSERT INTO roles (id, role) VALUES (2,'MANAGER')
INSERT INTO roles (id, role) VALUES (3,'ADMIN')
INSERT INTO roles (id, role) VALUES (4,'TEACHER')

/* email: admin@gmail.com password: 123123 | ROLES: [USER,ADMIN] */
INSERT INTO users (id, email, password, key_lastname, key_firstname, key_middlename, active, birth) VALUES (1, 'admin@gmail.com', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'Goncharov', 'Vitaliy', 'Dmitrievich', 1, STR_TO_DATE('1980-01-01', '%Y-%m-%d'))
INSERT INTO user_role (user_id, role_id) VALUES (1, 1), (1, 3)

/* email: user@gmail.com password: 123123 ROLES: [USER] */
INSERT INTO users (id, email, password, key_lastname, key_firstname, active, birth) VALUES (2, 'user@gmail.com', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'Kerrenson', 'David', 1, STR_TO_DATE('1980-01-01', '%Y-%m-%d'))
INSERT INTO user_role (user_id, role_id) VALUES (2, 1)

INSERT INTO locales (id, code) VALUES (1, 'ru')
INSERT INTO locales (id, code) VALUES (2, 'en')

INSERT INTO school (id, name, url) VALUES (1, 'school of economics and management', 'school_of_economics_and_management')
INSERT INTO school_translations (school_id, locale_id, title) VALUES (1, 1, 'школа экономики и менеджмента')

INSERT INTO major (id, name, url, duration, school_id, degree) VALUES (1, 'economics security', 'economics_security', 5, 1, 'specialist')
INSERT INTO major_translations (major_id, locale_id, title) VALUES (1, 1, 'экономическая безопасность')

INSERT INTO major (id, name, url, duration, school_id, degree) VALUES (2, 'management', 'management', 4, 1, 'bachelor')
INSERT INTO major_translations (major_id, locale_id, title) VALUES (2, 1, 'менеджмент')

INSERT INTO school (id, name, url) VALUES (2, 'school of natural sciences', 'school_of_natural_sciences')
INSERT INTO school_translations (school_id, locale_id, title) VALUES (2, 1, 'школа eстественных наук')

INSERT INTO school (id, name, url) VALUES (3, 'law school', 'law_school')
INSERT INTO school_translations (school_id, locale_id, title) VALUES (3, 1, 'школа юридических наук')

INSERT INTO _groups (course_num, major_id, name) VALUES (4, 1, '1401pd')
INSERT INTO group_translations (group_id, locale_id, title) VALUES (1, 1, '1401пд')

/* Create users with role TEACHER | Passwords are the same: 123123 | ROLES: [USER, TEACHER] */
INSERT INTO users (id, email, password, key_lastname, key_firstname, key_middlename, active, birth) VALUES (3, 'efremova.pv@dvfu.ru', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'efremova','polina','vitalyevna', 1, STR_TO_DATE('1980-01-01', '%Y-%m-%d'))
INSERT INTO users (id, email, password, key_lastname, key_firstname, key_middlename, active, birth) VALUES (4, 'vorobeva.lg@dvfu.ru', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'vorobyeva','larisa','gennadyevna', 1, STR_TO_DATE('1980-01-01', '%Y-%m-%d'))
INSERT INTO users (id, email, password, key_lastname, key_firstname, key_middlename, active, birth) VALUES (5, 'mishunina.ln@dvfu.ru', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'mishunina','lidia','nikolaevna', 1, STR_TO_DATE('1980-01-01', '%Y-%m-%d'))
INSERT INTO users (id, email, password, key_lastname, key_firstname, key_middlename, active, birth) VALUES (6, 'karpets.ov@dvfu.ru', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'karpets','olga','viktorovna', 1, STR_TO_DATE('1980-01-01', '%Y-%m-%d'))
INSERT INTO user_role (user_id, role_id) VALUES (3, 1), (3, 4)
INSERT INTO user_role (user_id, role_id) VALUES (4, 1), (4, 4)
INSERT INTO user_role (user_id, role_id) VALUES (5, 1), (5, 4)
INSERT INTO user_role (user_id, role_id) VALUES (6, 1), (6, 4)

INSERT INTO user_translations (user_id, locale_id, lastname, firstname, middlename) VALUES (3, 1, 'ефремова','полина','витальевна')
INSERT INTO user_translations (user_id, locale_id, lastname, firstname, middlename) VALUES (4, 1, 'воробьева','лариса','генадьевна')
INSERT INTO user_translations (user_id, locale_id, lastname, firstname, middlename) VALUES (5, 1, 'мишунина','лидия','николаевна')
/*INSERT INTO user_translations (user_id, locale_id, lastname, firstname, middlename) VALUES (6, 1, 'карпец','ольга','викторовна')*/

INSERT INTO subjects (id, name) VALUES (1, 'enterprise cost management')
INSERT INTO subjects (id, name) VALUES (2, 'pricing')
INSERT INTO subjects (id, name) VALUES (3, 'enterprise planning')
INSERT INTO subjects (id, name) VALUES (4, 'information security')
INSERT INTO subjects (id, name) VALUES (5, 'economic security')
INSERT INTO subjects (id, name) VALUES (6, 'criminal process')

/*INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (1, 1, 'управление затратами на предприятии')
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (2, 1, 'ценообразование')
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (3, 1, 'планирование на предприятии')
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (4, 1, 'информационная безопасность')
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (5, 1, 'экономическая безопасность')
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (6, 1, 'уголовный процесс')*/

INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (2, 4, 1, 2, 5, 1, NULL, 1, 'G427')
INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (1, 6, 1, 2, 6, 1, NULL, 1, 'G207')

INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (3, 2, 2, 2, 3, 1, 6, 2, 'G502')
INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (4, 2, 2, 1, 3, 1, 6, 2, 'G502')
INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (5, 3, 2, 1, 4, 1, 5, 2, 'G420')
INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (6, 3, 2, 2, 4, 1, 5, 2, 'G420')
INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (7, 6, 2, 1, 5, 1, NULL, 2, 'G420')
INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (8, 4, 2, 2, 5, 1, NULL, 2, 'G241')
INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (9, 1, 2, 1, 6, 1, 4, 1, 'G427')
INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (10, 1, 2, 2, 6, 1, 4, 1, 'G427')

INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (11, 5, 4, 1, 5, 1, NULL, 1, 'G211')
INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (12, 5, 4, 2, 5, 1, NULL, 1, 'G211')
INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (13, 5, 4, 1, 6, 1, 3, 2, 'G501')
INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (14, 5, 4, 2, 6, 1, 3, 2, 'G501')

INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (15, 1, 5, 1, 5, 1, 4, 2, 'G231')
INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (16, 1, 5, 2, 5, 1, 4, 2, 'G231')
INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (17, 2, 5, 1, 6, 1, 6, 1, 'G427')
INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (18, 3, 5, 2, 6, 1, 5, 1, 'G427')