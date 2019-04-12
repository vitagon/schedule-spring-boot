/* email: admin@gmail.com password: 123123 | ROLES: [USER,ADMIN] */
INSERT INTO users (id, email, password, key_lastname, key_firstname, key_middlename, active, birth) VALUES (6, 'admin@gmail.com', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'goncharov', 'vitaliy', 'dmitrievich', 1, TO_DATE('1980-01-01', '%Y-%m-%d'));
INSERT INTO user_role (user_id, role_id) VALUES (6, 5);

/* email: user@gmail.com password: 123123 ROLES: [USER] */
INSERT INTO users (id, email, password, key_lastname, key_firstname, active, birth) VALUES (7, 'user@gmail.com', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'Kerrenson', 'David', 1, TO_DATE('1980-01-01', '%Y-%m-%d'));

/* Create users with role TEACHER | Passwords are the same: 123123 | ROLES: [TEACHER] */
INSERT INTO users (id, email, password, key_lastname, key_firstname, key_middlename, active, birth) VALUES (8, 'efremova.pv@dvfu.ru', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'efremova','polina','vitalyevna', 1, TO_DATE('1980-01-01', '%Y-%m-%d'));
INSERT INTO users (id, email, password, key_lastname, key_firstname, key_middlename, active, birth) VALUES (9, 'vorobeva.lg@dvfu.ru', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'vorobyeva','larisa','gennadyevna', 1, TO_DATE('1980-01-01', '%Y-%m-%d'));
INSERT INTO users (id, email, password, key_lastname, key_firstname, key_middlename, active, birth) VALUES (10, 'mishunina.ln@dvfu.ru', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'mishunina','lidia','nikolaevna', 1, TO_DATE('1980-01-01', '%Y-%m-%d'));
INSERT INTO users (id, email, password, key_lastname, key_firstname, key_middlename, active, birth) VALUES (11, 'karpets.ov@dvfu.ru', '$2a$10$D5R2BLeMpFJ0GSCVQejLVetF0273XekBLOoPlSWNargBuRe/rinYm', 'karpets','olga','viktorovna', 1, TO_DATE('1980-01-01', '%Y-%m-%d'));
INSERT INTO user_role (user_id, role_id) VALUES (8, 3); 
INSERT INTO user_role (user_id, role_id) VALUES (9, 3);
INSERT INTO user_role (user_id, role_id) VALUES (10, 3);
INSERT INTO user_role (user_id, role_id) VALUES (11, 3);

INSERT INTO user_translations (user_id, locale_id, lastname, firstname, middlename) VALUES (8, 2, 'ефремова','полина','витальевна');
INSERT INTO user_translations (user_id, locale_id, lastname, firstname, middlename) VALUES (9, 2, 'воробьева','лариса','генадьевна');
INSERT INTO user_translations (user_id, locale_id, lastname, firstname, middlename) VALUES (10, 2, 'мишунина','лидия','николаевна');
INSERT INTO user_translations (user_id, locale_id, lastname, firstname, middlename) VALUES (11, 2, 'карпец','ольга','викторовна');

ALTER SEQUENCE hibernate_sequence RESTART WITH 12;