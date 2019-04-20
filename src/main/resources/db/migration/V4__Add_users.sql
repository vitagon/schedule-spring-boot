/* email: admin@gmail.com password: 123123 | ROLES: [USER,ADMIN] */
INSERT INTO users (id, username, email, password, key_lastname, key_firstname, key_middlename, active, birth, providerId) VALUES
	(6, 'admin', 'admin@gmail.com', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'goncharov', 'vitaliy', 'dmitrievich', TRUE, TO_DATE('1980-01-01', '%Y-%m-%d'), 'local');
INSERT INTO user_role (user_id, role_id) VALUES (6, 5);

/* email: user@gmail.com password: 123123 ROLES: [USER] */
INSERT INTO users (id, username, email, password, key_lastname, key_firstname, active, birth, providerId) VALUES
	(7, 'user', 'user@gmail.com', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'Kerrenson', 'David', TRUE, TO_DATE('1980-01-01', '%Y-%m-%d'), 'local');

/* Create users with role TEACHER | Passwords are the same: 123123 | ROLES: [TEACHER] */
INSERT INTO users (id, username, email, password, key_lastname, key_firstname, key_middlename, active, birth, providerId) VALUES
	(8, 'efremova.pv', 'efremova.pv@dvfu.ru', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'efremova','polina','vitalyevna', TRUE, TO_DATE('1980-01-01', '%Y-%m-%d'), 'local'),
	(9, 'vorobeva.lg', 'vorobeva.lg@dvfu.ru', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'vorobyeva','larisa','gennadyevna', TRUE, TO_DATE('1980-01-01', '%Y-%m-%d'), 'local'),
	(10, 'mishunina.ln', 'mishunina.ln@dvfu.ru', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'mishunina','lidia','nikolaevna', TRUE, TO_DATE('1980-01-01', '%Y-%m-%d'), 'local'),
	(11, 'karpets.ov', 'karpets.ov@dvfu.ru', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'karpets','olga','viktorovna', TRUE, TO_DATE('1980-01-01', '%Y-%m-%d'), 'local');
INSERT INTO user_role (user_id, role_id) VALUES (8, 3);
INSERT INTO user_role (user_id, role_id) VALUES (9, 3);
INSERT INTO user_role (user_id, role_id) VALUES (10, 3);
INSERT INTO user_role (user_id, role_id) VALUES (11, 3);

INSERT INTO user_translations (user_id, locale_id, lastname, firstname, middlename) VALUES
	(8, 2, 'ефремова','полина','витальевна'),
	(9, 2, 'воробьева','лариса','генадьевна'),
	(10, 2, 'мишунина','лидия','николаевна'),
	(11, 2, 'карпец','ольга','викторовна');

ALTER SEQUENCE hibernate_sequence RESTART WITH 12;