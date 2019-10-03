insert into _groups (id, name, course_num, major_id) values
  (17, 'S1114-38.05.01p', 4, 15),
  (18, 'S1115-38.05.01p', 5, 15);

ALTER SEQUENCE hibernate_sequence RESTART WITH 19;

insert into group_translations (group_id, locale_id, translation) values
	(17, 1, 'S1114-38.05.01p'),
	(17, 2, 'С1114-38.05.01п'),
	(18, 1, 'S1115-38.05.01p'),
	(18, 2, 'С1115-38.05.01п');