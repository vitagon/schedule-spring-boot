create table group_translations (
	group_id int4 not null,
	locale_id int4 not null,
	suffix_translation varchar(10) not null,
	primary key (group_id, locale_id)
);

alter table if exists group_translations
	add constraint FK_group_translations_groups
	foreign key (group_id) references _groups;
	
alter table if exists group_translations
	add constraint FK_group_translations_locales
	foreign key (locale_id) references locales;