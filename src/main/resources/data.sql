INSERT INTO roles (id, role) VALUES (1,'USER')
INSERT INTO roles (id, role) VALUES (2,'MANAGER')
INSERT INTO roles (id, role) VALUES (3,'ADMIN')

/* email: admin@gmail.com password: 123123 */
INSERT INTO users (id, email, password, first_name, last_name, active) VALUES (1, 'admin@gmail.com', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'Vitaliy', 'Goncharov', 1)
INSERT INTO user_role (user_id, role_id) VALUES (1, 3)

/* email: user@gmail.com password: 123123 */
INSERT INTO users (id, email, password, first_name, last_name, active) VALUES (2, 'user@gmail.com', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'David', 'Kerrenson', 1)
INSERT INTO user_role (user_id, role_id) VALUES (2, 1)

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

INSERT INTO _groups (course_num, major_id) VALUES (4, 1)
INSERT INTO group_translations (group_id, locale_id, title) VALUES (1,1,'С1401пд')
INSERT INTO group_translations (group_id, locale_id, title) VALUES (1,2,'S1401pd')


INSERT INTO teacher (id, mail) VALUES (1, 'efremova.pv@dvfu.ru')
INSERT INTO teacher (id, mail) VALUES (2, 'vorobeva.lg@dvfu.ru')
INSERT INTO teacher (id, mail) VALUES (3, 'mishunina.ln@dvfu.ru')
INSERT INTO teacher (id, mail) VALUES (4, 'karpets.ov@dvfu.ru')

INSERT INTO teacher_translations (teacher_id, locale_id, lastname, firstname, middlename) VALUES (1, 1, 'ефремова','полина','витальевна')
INSERT INTO teacher_translations (teacher_id, locale_id, lastname, firstname, middlename) VALUES (2, 1, 'воробьева','лариса','генадьевна')
INSERT INTO teacher_translations (teacher_id, locale_id, lastname, firstname, middlename) VALUES (3, 1, 'мишунина','лидия','николаевна')
INSERT INTO teacher_translations (teacher_id, locale_id, lastname, firstname, middlename) VALUES (4, 1, 'карпец','ольга','викторовна')

INSERT INTO subjects (id) VALUES (1)
INSERT INTO subjects (id) VALUES (2)
INSERT INTO subjects (id) VALUES (3)
INSERT INTO subjects (id) VALUES (4)
INSERT INTO subjects (id) VALUES (5)
INSERT INTO subjects (id) VALUES (6)

INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (1, 1, 'управление затратами на предприятии')
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (2, 1, 'ценообразование')
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (3, 1, 'планирование на предприятии')
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (4, 1, 'информационная безопасность')
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (5, 1, 'экономическая безопасность')
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (6, 1, 'уголовный процесс')

INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (1, 2, 'enterprise cost management')
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (2, 2, 'pricing')
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (3, 2, 'enterprise planning')
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (4, 2, 'information security')
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (5, 2, 'economic security')
INSERT INTO subject_translations (subject_id, locale_id, title) VALUES (6, 2, 'criminal process')

INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, teacher_id, lesson_type, classroom) VALUES (1, 6, 1, 2, 6, 1, NULL, 1, 'G207')
INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, teacher_id, lesson_type, classroom) VALUES (2, 4, 1, 2, 5, 1, NULL, 1, 'G427')

INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, teacher_id, lesson_type, classroom) VALUES (3, 2, 2, 2, 3, 1, 4, 2, 'G502')
INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, teacher_id, lesson_type, classroom) VALUES (4, 2, 2, 1, 3, 1, 4, 2, 'G502')
INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, teacher_id, lesson_type, classroom) VALUES (5, 3, 2, 1, 4, 1, 3, 2, 'G420')
INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, teacher_id, lesson_type, classroom) VALUES (6, 3, 2, 2, 4, 1, 3, 2, 'G420')
INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, teacher_id, lesson_type, classroom) VALUES (7, 6, 2, 1, 5, 1, NULL, 2, 'G420')
INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, teacher_id, lesson_type, classroom) VALUES (8, 4, 2, 2, 5, 1, NULL, 2, 'G241')
INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, teacher_id, lesson_type, classroom) VALUES (9, 1, 2, 1, 6, 1, 2, 1, 'G427')
INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, teacher_id, lesson_type, classroom) VALUES (10, 1, 2, 2, 6, 1, 2, 1, 'G427')

INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, teacher_id, lesson_type, classroom) VALUES (11, 5, 4, 1, 5, 1, NULL, 1, 'G211')
INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, teacher_id, lesson_type, classroom) VALUES (12, 5, 4, 2, 5, 1, NULL, 1, 'G211')
INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, teacher_id, lesson_type, classroom) VALUES (13, 5, 4, 1, 6, 1, 1, 2, 'G501')
INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, teacher_id, lesson_type, classroom) VALUES (14, 5, 4, 2, 6, 1, 1, 2, 'G501')

INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, teacher_id, lesson_type, classroom) VALUES (15, 1, 5, 1, 5, 1, 2, 2, 'G231')
INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, teacher_id, lesson_type, classroom) VALUES (16, 1, 5, 2, 5, 1, 2, 2, 'G231')
INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, teacher_id, lesson_type, classroom) VALUES (17, 2, 5, 1, 6, 1, 4, 1, 'G427')
INSERT INTO schedules (id, subject_id, day_num, week_type, lesson_num, group_id, teacher_id, lesson_type, classroom) VALUES (18, 3, 5, 2, 6, 1, 3, 1, 'G427')