INSERT INTO school (id, name, url) VALUES (12, 'school of economics and management', 'school_of_economics_and_management');
INSERT INTO school_translations (school_id, locale_id, title) VALUES (12, 2, 'школа экономики и менеджмента');

INSERT INTO school (id, name, url) VALUES (13, 'school of natural sciences', 'school_of_natural_sciences');
INSERT INTO school_translations (school_id, locale_id, title) VALUES (13, 2, 'школа eстественных наук');

INSERT INTO school (id, name, url) VALUES (14, 'law school', 'law_school');
INSERT INTO school_translations (school_id, locale_id, title) VALUES (14, 2, 'школа юридических наук');

ALTER SEQUENCE hibernate_sequence RESTART WITH 15;