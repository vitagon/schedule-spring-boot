drop table if exists _groups cascade;

create table _groups (
	id serial not null,
  name varchar(255) not null,
	course_num int4 not null,
	major_id int4 not null,
	primary key (id)
);

alter table if exists _groups
  add constraint UQ__groups_name
  unique(name);

alter table if exists _groups
	add constraint FK__groups_major
	foreign key (major_id) references majors;
