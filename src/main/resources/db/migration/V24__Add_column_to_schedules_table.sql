alter table if exists schedules
  add column subgroup_id int4;

alter table if exists schedules
  add constraint FK_schedules_subgroup
  foreign key (subgroup_id) references subgroups;