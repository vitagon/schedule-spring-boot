create sequence hibernate_sequence start 1 increment 1;

create table _groups (
	id  serial not null,
	course_num int4 not null,
	number int4 not null,
	suffix varchar(255) not null,
	major_id int4 not null,
	primary key (id)
);

create table locales (
	id  serial not null,
	code varchar(255),
	primary key (id)
);

create table majors (
	id  serial not null,
	degree int4 not null,
	duration int4 not null,
	name varchar(255) not null,
	url varchar(255) not null,
	school_id int4 not null,
	primary key (id)
);

create table major_translations (
	title varchar(255),
	major_id int4 not null,
	locale_id int4 not null,
	primary key (locale_id, major_id)
);

create table roles (
	id  serial not null,
	role varchar(255),
	primary key (id)
);

create table schedules (
	id  serial not null,
	classroom varchar(6),
	day_num int4,
	lesson_num int4,
	lesson_type int4,
	week_type varchar(4),
	group_id int4,
	subject_id int4,
	user_id int4,
	primary key (id)
);

create table schools (
	id  serial not null,
	name varchar(255) not null,
	url varchar(255) not null,
	primary key (id)
);

create table school_translations (
	title varchar(255),
	school_id int4 not null,
	locale_id int4 not null,
	primary key (locale_id, school_id)
);

create table subject_translations (
	title varchar(255),
	subject_id int4 not null,
	locale_id int4 not null,
	primary key (locale_id, subject_id)
);

create table subjects (
	id  serial not null,
	name varchar(255),
	primary key (id)
);

create table user_role (
	user_id int4 not null,
	role_id int4 not null,
	primary key (user_id, role_id)
);

create table user_translations (
	firstname varchar(255),
	lastname varchar(255),
	middlename varchar(255),
	user_id int4 not null,
	locale_id int4 not null,
	primary key (locale_id, user_id)
);

create table users (
	id  serial not null,
	username varchar(255) not null,
	email varchar(255),
	password varchar(255) not null,
	key_firstname varchar(255),
	key_lastname varchar(255),
	key_middlename varchar(255),
	birth date,
	active boolean not null,
	provider_id varchar(255) not null,
	primary key (id)
);

create table userconnection (
	userId varchar(255) not null,
	providerId varchar(255) not null,
	providerUserId varchar(255),
	rank int not null,
	displayName varchar(255),
	profileUrl varchar(512),
	imageUrl varchar(512),
	accessToken varchar(512) not null,
	secret varchar(512),
	refreshToken varchar(512),
	expireTime int8,
	primary key (userId, providerId, providerUserId)
);


alter table if exists _groups
	add constraint UQ__groups_major_id_number
	unique (major_id, number, suffix);

alter table if exists majors
	add constraint UQ_major_name_url
	unique (name, url);
	
alter table if exists majors
	add constraint FK_major_school
	foreign key (school_id) references schools;
	
alter table if exists schedules
	add constraint UQ_schedules_group_id_day_num_week_type_lesson_num
	unique (group_id, day_num, week_type, lesson_num);

alter table if exists schools
	add constraint UQ_school_name_url
	unique (name, url);

alter table if exists _groups
	add constraint FK__groups_major
	foreign key (major_id) references majors;

alter table if exists major_translations
	add constraint FK_major_translations_major
	foreign key (major_id) references majors;
	
alter table if exists major_translations
	add constraint FK_major_translations_locales
	foreign key (locale_id) references locales;
	
alter table if exists schedules
	add constraint FK_schedules__groups
	foreign key (group_id) references _groups;
	
alter table if exists schedules
	add constraint FK_schedules_subjects
	foreign key (subject_id) references subjects;

alter table if exists schedules
	add constraint FK_schedules_users
	foreign key (user_id) references users;

alter table if exists school_translations
	add constraint FK_school_translations_school
	foreign key (school_id) references schools;

alter table if exists school_translations
	add constraint FK_school_translations_locales
	foreign key (locale_id) references locales;
	
alter table if exists subject_translations
	add constraint FK_subject_translations_subjects
	foreign key (subject_id) references subjects;

alter table if exists subject_translations
	add constraint FK_subject_translations_locales
	foreign key (locale_id) references locales;

alter table if exists user_role
	add constraint FK_user_role_roles
	foreign key (role_id) references roles;
	
alter table if exists user_role
	add constraint FK_user_role_users
	foreign key (user_id) references users;

alter table if exists user_translations
	add constraint FK_user_translations_users
	foreign key (user_id) references users;

alter table if exists user_translations
	add constraint FK_user_translations_locales
	foreign key (locale_id) references locales;
	
alter table if exists users
	add constraint UQ_users_username
	unique (username);
	
alter table if exists userconnection
	add constraint UQ_userconnection_userId_providerId_rank
	unique (userId, providerId, rank);