alter table if exists schedules
  add constraint FK_schedules__groups
  foreign key (group_id) references _groups;