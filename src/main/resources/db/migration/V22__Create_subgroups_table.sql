create table subgroups (
  id serial not null,
  name varchar(10) not null unique,
  primary key (id)
);