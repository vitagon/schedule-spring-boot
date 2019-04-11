INSERT INTO roles (id, role) VALUES (1, 'TEACHER');
INSERT INTO roles (id, role) VALUES (2, 'MANAGER');
INSERT INTO roles (id, role) VALUES (3, 'ADMIN');

ALTER SEQUENCE roles_id_seq RESTART WITH 3;