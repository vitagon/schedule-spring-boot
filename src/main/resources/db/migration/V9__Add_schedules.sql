INSERT INTO schedules (id, day_num, week_type, lesson_num, group_id, subject_id, user_id, lesson_type, classroom) VALUES (1, 1, 'down', 5, 1, 4, NULL, 1, 'G427');
INSERT INTO schedules (id, day_num, week_type, lesson_num, group_id, subject_id, user_id, lesson_type, classroom) VALUES (2, 1, 'down', 6, 1, 6, NULL, 1, 'G207');
                                                                        
INSERT INTO schedules (id, day_num, week_type, lesson_num, group_id, subject_id, user_id, lesson_type, classroom) VALUES (3, 2, 'down', 3, 1, 2, 6, 2, 'G502');
INSERT INTO schedules (id, day_num, week_type, lesson_num, group_id, subject_id, user_id, lesson_type, classroom) VALUES (4, 2, 'up', 3, 1, 2, 6, 2, 'G502');
INSERT INTO schedules (id, day_num, week_type, lesson_num, group_id, subject_id, user_id, lesson_type, classroom) VALUES (5, 2, 'up', 4, 1, 3, 5, 2, 'G420');
INSERT INTO schedules (id, day_num, week_type, lesson_num, group_id, subject_id, user_id, lesson_type, classroom) VALUES (6, 2, 'down', 4, 1, 3, 5, 2, 'G420');
INSERT INTO schedules (id, day_num, week_type, lesson_num, group_id, subject_id, user_id, lesson_type, classroom) VALUES (7, 2, 'up', 5, 1, 6, NULL, 2, 'G420');
INSERT INTO schedules (id, day_num, week_type, lesson_num, group_id, subject_id, user_id, lesson_type, classroom) VALUES (8, 2, 'down', 5, 1, 4, NULL, 2, 'G241');
INSERT INTO schedules (id, day_num, week_type, lesson_num, group_id, subject_id, user_id, lesson_type, classroom) VALUES (9, 2, 'up', 6, 1, 1, 4, 1, 'G427');
INSERT INTO schedules (id, day_num, week_type, lesson_num, group_id, subject_id, user_id, lesson_type, classroom) VALUES (10, 2, 'down', 6, 1, 1, 4, 1, 'G427');
 
INSERT INTO schedules (id, day_num, week_type, lesson_num, group_id, subject_id, user_id, lesson_type, classroom) VALUES (11, 4, 'up', 5, 1, 5, NULL, 1, 'G211');
INSERT INTO schedules (id, day_num, week_type, lesson_num, group_id, subject_id, user_id, lesson_type, classroom) VALUES (12, 4, 'down', 5, 1, 5, NULL, 1, 'G211');
INSERT INTO schedules (id, day_num, week_type, lesson_num, group_id, subject_id, user_id, lesson_type, classroom) VALUES (13, 4, 'up', 6, 1, 5, 3, 2, 'G501');
INSERT INTO schedules (id, day_num, week_type, lesson_num, group_id, subject_id, user_id, lesson_type, classroom) VALUES (14, 4, 'down', 6, 1, 5, 3, 2, 'G501');

INSERT INTO schedules (id, day_num, week_type, lesson_num, group_id, subject_id, user_id, lesson_type, classroom) VALUES (15, 5, 'up', 5, 1, 1, 4, 2, 'G231');
INSERT INTO schedules (id, day_num, week_type, lesson_num, group_id, subject_id, user_id, lesson_type, classroom) VALUES (16, 5, 'down', 5, 1, 1, 4, 2, 'G231');
INSERT INTO schedules (id, day_num, week_type, lesson_num, group_id, subject_id, user_id, lesson_type, classroom) VALUES (17, 5, 'up', 6, 1, 2, 6, 1, 'G427');
INSERT INTO schedules (id, day_num, week_type, lesson_num, group_id, subject_id, user_id, lesson_type, classroom) VALUES (18, 5, 'down', 6, 1, 3, 5, 1, 'G427');

ALTER SEQUENCE schedules_id_seq RESTART WITH 18;