/* email: admin@gmail.com password: 123123 | ROLES: [USER,ADMIN] */
INSERT INTO users (id, username, email, password, lastname, firstname, middlename, active, birth, provider_id) VALUES
	(6, 'admin', 'admin@gmail.com', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'goncharov', 'vitaliy', 'dmitrievich', TRUE, TO_DATE('1980-01-01', '%Y-%m-%d'), 'local');
INSERT INTO user_role (user_id, role_id) VALUES (6, 5);

/* email: user@gmail.com password: 123123 ROLES: [USER] */
INSERT INTO users (id, username, email, password, lastname, firstname, active, birth, provider_id) VALUES
	(7, 'user', 'user@gmail.com', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'Kerrenson', 'David', TRUE, TO_DATE('1980-01-01', '%Y-%m-%d'), 'local');

/* Create users with role TEACHER | Passwords are the same: 123123 | ROLES: [TEACHER] */
INSERT INTO users (id, username, email, password, lastname, firstname, middlename, active, birth, provider_id) VALUES
	(8, 'efremova.pv', 'efremova.pv@dvfu.ru', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'efremova','polina','vitalyevna', TRUE, TO_DATE('1980-01-01', '%Y-%m-%d'), 'local'),
	(9, 'vorobeva.lg', 'vorobeva.lg@dvfu.ru', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'vorobyeva','larisa','gennadyevna', TRUE, TO_DATE('1980-01-01', '%Y-%m-%d'), 'local'),
	(10, 'mishunina.ln', 'mishunina.ln@dvfu.ru', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'mishunina','lidia','nikolaevna', TRUE, TO_DATE('1980-01-01', '%Y-%m-%d'), 'local'),
	(11, 'karpets.ov', 'karpets.ov@dvfu.ru', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'karpets','olga','viktorovna', TRUE, TO_DATE('1980-01-01', '%Y-%m-%d'), 'local');
	
INSERT INTO user_role (user_id, role_id) VALUES 
	(8, 3),
	(9, 3),
	(10, 3),
	(11, 3);

ALTER SEQUENCE hibernate_sequence RESTART WITH 12;