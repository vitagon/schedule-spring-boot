INSERT INTO roles (role) VALUES ('USER')
INSERT INTO roles (role) VALUES ('MANAGER')
INSERT INTO roles (role) VALUES ('ADMIN')
INSERT INTO roles (role) VALUES ('TEACHER')

/* email: admin@gmail.com password: 123123 | ROLES: [USER,ADMIN] */
INSERT INTO users (email, password, key_lastname, key_firstname, key_middlename, active, birth) VALUES ('admin@gmail.com', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'Goncharov', 'Vitaliy', 'Dmitrievich', 1, TO_DATE('1980-01-01', '%Y-%m-%d'))
INSERT INTO user_role (user_id, role_id) VALUES (1, 1), (1, 3)

/* email: user@gmail.com password: 123123 ROLES: [USER] */
INSERT INTO users (email, password, key_lastname, key_firstname, active, birth) VALUES ('user@gmail.com', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'Kerrenson', 'David', 1, TO_DATE('1980-01-01', '%Y-%m-%d'))
INSERT INTO user_role (user_id, role_id) VALUES (2, 1)

INSERT INTO locales (code) VALUES ('ru')
INSERT INTO locales (code) VALUES ('en')

INSERT INTO school (name, url) VALUES ('school of economics and management', 'school_of_economics_and_management')
INSERT INTO school_translations (school_id, locale_id, title) VALUES (1, 1, 'школа экономики и менеджмента')

INSERT INTO major (name, url, duration, school_id, degree) VALUES ('economics security', 'economics_security', 5, 1, 2)
INSERT INTO major_translations (major_id, locale_id, title) VALUES (1, 1, 'экономическая безопасность')

INSERT INTO major (name, url, duration, school_id, degree) VALUES ('management', 'management', 4, 1, 1)
INSERT INTO major_translations (major_id, locale_id, title) VALUES (2, 1, 'менеджмент')

INSERT INTO school (name, url) VALUES ('school of natural sciences', 'school_of_natural_sciences')
INSERT INTO school_translations (school_id, locale_id, title) VALUES (2, 1, 'школа eстественных наук')

INSERT INTO school (name, url) VALUES ('law school', 'law_school')
INSERT INTO school_translations (school_id, locale_id, title) VALUES (3, 1, 'школа юридических наук')

INSERT INTO _groups (course_num, major_id, number, suffix) VALUES (4, 1, 1401, 'pd')

/* Create users with role TEACHER | Passwords are the same: 123123 | ROLES: [USER, TEACHER] */
INSERT INTO users (email, password, key_lastname, key_firstname, key_middlename, active, birth) VALUES ('efremova.pv@dvfu.ru', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'efremova','polina','vitalyevna', 1, TO_DATE('1980-01-01', '%Y-%m-%d'))
INSERT INTO users (email, password, key_lastname, key_firstname, key_middlename, active, birth) VALUES ('vorobeva.lg@dvfu.ru', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'vorobyeva','larisa','gennadyevna', 1, TO_DATE('1980-01-01', '%Y-%m-%d'))
INSERT INTO users (email, password, key_lastname, key_firstname, key_middlename, active, birth) VALUES ('mishunina.ln@dvfu.ru', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'mishunina','lidia','nikolaevna', 1, TO_DATE('1980-01-01', '%Y-%m-%d'))
INSERT INTO users (email, password, key_lastname, key_firstname, key_middlename, active, birth) VALUES ('karpets.ov@dvfu.ru', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'karpets','olga','viktorovna', 1, TO_DATE('1980-01-01', '%Y-%m-%d'))
INSERT INTO user_role (user_id, role_id) VALUES (3, 1), (3, 4)
INSERT INTO user_role (user_id, role_id) VALUES (4, 1), (4, 4)
INSERT INTO user_role (user_id, role_id) VALUES (5, 1), (5, 4)
INSERT INTO user_role (user_id, role_id) VALUES (6, 1), (6, 4)

INSERT INTO user_translations (user_id, locale_id, lastname, firstname, middlename) VALUES (3, 1, 'ефремова','полина','витальевна')
INSERT INTO user_translations (user_id, locale_id, lastname, firstname, middlename) VALUES (4, 1, 'воробьева','лариса','генадьевна')
INSERT INTO user_translations (user_id, locale_id, lastname, firstname, middlename) VALUES (5, 1, 'мишунина','лидия','николаевна')
/*INSERT INTO user_translations (user_id, locale_id, lastname, firstname, middlename) VALUES (6, 1, 'карпец','ольга','викторовна')*/

INSERT INTO subjects (name) VALUES ('enterprise cost management')
INSERT INTO subjects (name) VALUES ('pricing')
INSERT INTO subjects (name) VALUES ('enterprise planning')
INSERT INTO subjects (name) VALUES ('information security')
INSERT INTO subjects (name) VALUES ('economic security')
INSERT INTO subjects (name) VALUES ('criminal process')

/**/
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (1, 1, 'управление затратами на предприятии')
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (2, 1, 'ценообразование')
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (3, 1, 'планирование на предприятии')
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (4, 1, 'информационная безопасность')
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (5, 1, 'экономическая безопасность')
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (6, 1, 'уголовный процесс')
/**/

INSERT INTO schedules (subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (4, 1, 'down', 5, 1, NULL, 1, 'G427')
INSERT INTO schedules (subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (6, 1, 'down', 6, 1, NULL, 1, 'G207')

INSERT INTO schedules (subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (2, 2, 'down', 3, 1, 6, 2, 'G502')
INSERT INTO schedules (subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (2, 2, 'up', 3, 1, 6, 2, 'G502')
INSERT INTO schedules (subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (3, 2, 'up', 4, 1, 5, 2, 'G420')
INSERT INTO schedules (subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (3, 2, 'down', 4, 1, 5, 2, 'G420')
INSERT INTO schedules (subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (6, 2, 'up', 5, 1, NULL, 2, 'G420')
INSERT INTO schedules (subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (4, 2, 'down', 5, 1, NULL, 2, 'G241')
INSERT INTO schedules (subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (1, 2, 'up', 6, 1, 4, 1, 'G427')
INSERT INTO schedules (subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (1, 2, 'down', 6, 1, 4, 1, 'G427')

INSERT INTO schedules (subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (5, 4, 'up', 5, 1, NULL, 1, 'G211')
INSERT INTO schedules (subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (5, 4, 'down', 5, 1, NULL, 1, 'G211')
INSERT INTO schedules (subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (5, 4, 'up', 6, 1, 3, 2, 'G501')
INSERT INTO schedules (subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (5, 4, 'down', 6, 1, 3, 2, 'G501')

INSERT INTO schedules (subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (1, 5, 'up', 5, 1, 4, 2, 'G231')
INSERT INTO schedules (subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (1, 5, 'down', 5, 1, 4, 2, 'G231')
INSERT INTO schedules (subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (2, 5, 'up', 6, 1, 6, 1, 'G427')
INSERT INTO schedules (subject_id, day_num, week_type, lesson_num, group_id, user_id, lesson_type, classroom) VALUES (3, 5, 'down', 6, 1, 5, 1, 'G427')