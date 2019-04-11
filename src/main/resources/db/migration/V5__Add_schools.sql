INSERT INTO school (id, name, url) VALUES (1, 'school of economics and management', 'school_of_economics_and_management');
INSERT INTO school_translations (school_id, locale_id, title) VALUES (1, 1, 'школа экономики и менеджмента');

INSERT INTO school (id, name, url) VALUES (2, 'school of natural sciences', 'school_of_natural_sciences');
INSERT INTO school_translations (school_id, locale_id, title) VALUES (2, 1, 'школа eстественных наук');

INSERT INTO school (id, name, url) VALUES (3, 'law school', 'law_school');
INSERT INTO school_translations (school_id, locale_id, title) VALUES (3, 1, 'школа юридических наук');

ALTER SEQUENCE school_id_seq RESTART WITH 3;