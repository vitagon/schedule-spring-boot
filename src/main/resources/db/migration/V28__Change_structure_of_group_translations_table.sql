truncate table group_translations;

alter table if exists group_translations
  drop column if exists suffix_translation;

alter table if exists group_translations
  add column translation varchar(30) not null;