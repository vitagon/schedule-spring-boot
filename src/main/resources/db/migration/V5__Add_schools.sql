INSERT INTO schools (id, name, url) VALUES
  (12, 'school of economics and management', 'school_of_economics_and_management'),
  (13, 'school of natural sciences', 'school_of_natural_sciences'),
  (14, 'law school', 'law_school');

ALTER SEQUENCE hibernate_sequence RESTART WITH 15;

INSERT INTO school_translations (school_id, locale_id, translation) VALUES
	(12, 1, 'school of economics and management'),
  (12, 2, 'школа экономики и менеджмента'),
	(13, 1, 'school of natural sciences'),
  (13, 2, 'школа eстественных наук'),
	(14, 1, 'law school'),
  (14, 2, 'школа юридических наук');