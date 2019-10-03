create table lessons_time (
	id serial not null,
	lesson_num int4 not null,
	lesson_time varchar(11) not null,
	primary key (lesson_num)
)